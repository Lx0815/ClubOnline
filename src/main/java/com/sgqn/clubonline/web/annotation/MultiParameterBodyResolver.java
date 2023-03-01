package com.sgqn.clubonline.web.annotation;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgqn.clubonline.web.exception.BadParameterException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;


/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2022-12-16 10:08:15
 * @modify:
 */

@Component
public class MultiParameterBodyResolver implements HandlerMethodArgumentResolver {

    /**
     * 在 request 域中缓存请求体的键名称，
     */
    private static final String JSON_REQUEST_BODY = "JSON_REQUEST_BODY";

    /**
     * 注入 ObjectMapper
     */
    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(MultiParameterBody.class);
    }

    @Override
    public Object resolveArgument(@NotNull MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  @NotNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Object result;
        Object value;
        // 获取请求对象
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        // 查看是否存在缓存，如果前面有参数被标注过 @MultiParameterBody 并且成功解析，那么此处应存在请求体
        String requestBody = (String) webRequest.getAttribute(JSON_REQUEST_BODY, NativeWebRequest.SCOPE_REQUEST);
        if (ObjectUtils.isEmpty(requestBody)) {
            // 第一次解析，请求体中没有值
            try (BufferedReader br = Objects.requireNonNull(request).getReader()) {

                // 使用工具类将 缓冲输入流 读取到 responseBody 中
                requestBody = IOUtils.toString(br);
                // 加入请求域，作为缓存
                webRequest.setAttribute(JSON_REQUEST_BODY, requestBody, NativeWebRequest.SCOPE_REQUEST);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 进行配置
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        JsonNode rootNode = objectMapper.readTree(requestBody);
        // JSON 串为空抛出异常
        Assert.notNull(rootNode, "参数为" + requestBody + " null");
        // 获取注解
        MultiParameterBody multiParameterBody = parameter.getParameterAnnotation(MultiParameterBody.class);
        Assert.notNull(multiParameterBody, "参数" + requestBody + "不存在 MultiRequestBody 注解");

        String key = multiParameterBody.value();
        // 根据注解 value 解析 JSON 串，如果没有根据参数的名字解析 JSON
        if (!StringUtils.isNoneBlank(key)) {
            key = parameter.getParameterName();
        }
        value = rootNode.get(key);
        // 如果为参数必填但未根据 key 成功得到对应 value 抛出异常
        Assert.isTrue(multiParameterBody.required() && ObjectUtils.isEmpty(value), key + "为必填参数，但为空");

        // 获取参数的类型
        Class<?> parameterType = parameter.getParameterType();
        // 成功从 JSON 解析到对应 key 的 value
        if (!ObjectUtils.isEmpty(value)) {
            return objectMapper.readValue(value.toString(), parameterType);
        }

        // 未从 JSON 解析到对应 key（可能是注解的 value 或者是参数名字） 的值，要么没传值，要么传的名字不对
        // 如果参数为基本数据类型，且为必传参数抛出异常
        Assert.isTrue((ClassUtils.isPrimitiveWrapper(parameterType) && multiParameterBody.required()), String.format("必填参数 %s 没有找到", key));
        // 参数非基本数据类型，如果不允许解析外层属性，且为必传参数 报错抛出异常
        Assert.isTrue(!ClassUtils.isPrimitiveWrapper(parameterType) && !multiParameterBody.parseAllFields() && multiParameterBody.required(),
                String.format("必填参数 %s 没有找到", key));

        // 既然找不到对应参数，而且非基本类型，我们可以解析外层属性，将整个 JSON 作为参数进行解析。解析失败会抛出异常
        result = objectMapper.readValue(requestBody, parameterType);
        // 必填参数若为 null 则 抛出异常
        if (multiParameterBody.required() && ObjectUtils.isEmpty(result)) {
            throw new BadParameterException("必填参数 " + parameter.getParameterName() + " 为 null");
        }
        return result;
    }
}