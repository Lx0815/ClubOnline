DROP DATABASE IF EXISTS club_manager_server;
CREATE DATABASE club_manager_server;

/*====================================
  权限相关，以 per（permissions 的缩写） 为前缀，
  包含：
    - per_user
    - per_user_detail
    - per_login_user
    - per_ud__lu__u_mid
    - per_role
    - per_menu
    - per_menu_role_mid

    ======================================*/

CREATE TABLE per_user
(
    `id`                 INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `avatar`             VARCHAR(511) NOT NULL DEFAULT 'https://www.awind.space/blog_images/202302102319803.png' COMMENT '用户头像',
    `nick_name`          VARCHAR(15)  NOT NULL COMMENT '用户昵称',
    `personal_signature` VARCHAR(127)          DEFAULT '' COMMENT '个性签名',
    `create_date_time`   DATETIME     NOT NULL COMMENT '创建时间',
    `update_date_time`   DATETIME     NOT NULL COMMENT '修改时间',
    `is_deleted`         TINYINT      NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '简单用户表，不包含重要信息。可供其他用户查看';

CREATE TABLE per_user_detail
(
    `id`                     INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `real_name`              VARCHAR(31)         DEFAULT '' COMMENT '姓名',
    `birth_date`             DATE                DEFAULT '1970-01-01' COMMENT '出生日期',
    `ethnic_group`           VARCHAR(15)         DEFAULT '' COMMENT '民族',
    `political_landscape`    VARCHAR(15)         DEFAULT '' COMMENT '政治面貌',
    `level_two_college`      VARCHAR(63)         DEFAULT '' COMMENT '二级学院',
    `major`                  VARCHAR(31)         DEFAULT '' COMMENT '专业，per_major.id',
    `grade`                  INT                 DEFAULT -1 COMMENT '年级',
    `class`                  VARCHAR(15)         DEFAULT '' COMMENT '班级序号',
    `contact_number`         VARCHAR(15) UNIQUE  DEFAULT '' COMMENT '联系电话',
    `qq`                     VARCHAR(11) UNIQUE  DEFAULT '' COMMENT 'QQ',
    `interested_department`  VARCHAR(15)         DEFAULT '' COMMENT '意向部门',
    `is_obey_adjustment`     TINYINT             DEFAULT 1 COMMENT '是否服从调剂，1 表示服从， 0 表示 不服从',
    `hobbies`                VARCHAR(255)        DEFAULT '' COMMENT '爱好及特长',
    `awarded_awards`         VARCHAR(255)        DEFAULT '' COMMENT '曾获奖项',
    `career_experience`      VARCHAR(255)        DEFAULT '' COMMENT '任职经历',
    `insights_into_the_club` VARCHAR(255)        DEFAULT '' COMMENT '对社团的认识',
    `photo`                  VARCHAR(511) UNIQUE DEFAULT '' COMMENT '二寸电子照片',
    `create_date_time`       DATETIME NOT NULL COMMENT '创建时间',
    `update_date_time`       DATETIME NOT NULL COMMENT '修改时间',
    `is_deleted`             TINYINT  NOT NULL   DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '用户详情表，包含用户详细的个人信息。需加以保护';

CREATE TABLE per_login_user
(
    `id`               INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `phone_num`        VARCHAR(15) NOT NULL COMMENT '手机号',
    `password`         CHAR(60)    NOT NULL COMMENT '密码，使用 bcrypt 算法进行加密，默认情况下长度为 60',
    `email`            VARCHAR(31) UNIQUE COMMENT '邮箱，也可以使用邮箱进行登录',
    `create_date_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_date_time` DATETIME    NOT NULL COMMENT '修改时间',
    `is_deleted`       TINYINT     NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '用户登录信息表';

CREATE TABLE per_ud__lu__u_mid
(
    `id`               INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `u_id`             INT      NOT NULL UNIQUE COMMENT 'user.id',
    `lu_id`            INT      NOT NULL UNIQUE COMMENT 'login_user.id',
    `ud_id`            INT      NOT NULL UNIQUE COMMENT 'user_detail.id',
    `create_date_time` DATETIME NOT NULL COMMENT '创建时间',
    `update_date_time` DATETIME NOT NULL COMMENT '修改时间',
    `is_deleted`       TINYINT  NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '用户表、用户详情表、用户登录信息表的中间表，用以关联三张表';

CREATE TABLE per_major
(
    `id`               INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `code`             VARCHAR(15) NOT NULL COMMENT '专业代码',
    `name`             VARCHAR(31) NOT NULL COMMENT '名称',
    `category`         VARCHAR(31) NOT NULL COMMENT '专业类别',
    `create_date_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_date_time` DATETIME    NOT NULL COMMENT '修改时间',
    `is_deleted`       TINYINT     NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '专业表';

CREATE TABLE per_role
(
    `id`               INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `name`             VARCHAR(7) NOT NULL COMMENT '角色名称',
    `code`             CHAR(31)   NOT NULL COMMENT '角色代码，',
    `description`      VARCHAR(31)         DEFAULT '' COMMENT '描述',
    `create_date_time` DATETIME   NOT NULL COMMENT '创建时间',
    `update_date_time` DATETIME   NOT NULL COMMENT '修改时间',
    `is_deleted`       TINYINT    NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '权限角色表';

CREATE TABLE per_menu
(
    `id`               INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `parent_id`        INT COMMENT '父菜单的 id',
    `name`             VARCHAR(31) NOT NULL COMMENT '名称',
    `description`      VARCHAR(31)          DEFAULT '' COMMENT '描述',
    `create_date_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_date_time` DATETIME    NOT NULL COMMENT '修改时间',
    `is_deleted`       TINYINT     NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '权限带单表';

CREATE TABLE per_menu__role_mid
(
    `id`               INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `menu_id`          INT      NOT NULL COMMENT 'per_menu.id',
    `role_id`          INT      NOT NULL COMMENT 'per_role.id',
    `create_date_time` DATETIME NOT NULL COMMENT '创建时间',
    `update_date_time` DATETIME NOT NULL COMMENT '修改时间',
    `is_deleted`       TINYINT  NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '权限菜单表和权限角色表的中间表';

/*====================================
  社团相关，以 cl（club 的缩写） 为前缀，
  包含：
    - cl_club
    - cl_per_user__cl_club__per_role_mid
    - cl_club_image
    - cl_club_activity_image
    - cl_club_activity
    - cl_club_post
    - cl_club_post_remark
    - cl_club_activity_status

    ======================================*/
CREATE TABLE cl_club
(
    `id`               INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `establish_date`   DATE        NOT NULL COMMENT '成立时间',
    `avatar`           VARCHAR(511)         DEFAULT 'https://www.awind.space/blog_images/202302102319803.png' COMMENT '头像',
    `name`             VARCHAR(15) NOT NULL COMMENT '名称',
    `announcement`     VARCHAR(1023)        DEFAULT '' COMMENT '公告',
    `live_ness`        INT                  DEFAULT 50 COMMENT '活跃度，初始 50，最高无上限',
    `people_num`       INT                  DEFAULT 0 COMMENT '人数',
    `belong_college`   VARCHAR(31) NOT NULL COMMENT '所属的二级学院',
    `create_date_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_date_time` DATETIME    NOT NULL COMMENT '修改时间',
    `is_deleted`       TINYINT     NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '社团表';

CREATE TABLE cl_per_user__cl_club__per_role_mid
(
    `id`               INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `club_id`          INT COMMENT 'cl_club.id',
    `user_id`          INT      NOT NULL COMMENT 'per_user.id',
    `role_id`          INT      NOT NULL COMMENT 'per_role.id',
    `create_date_time` DATETIME NOT NULL COMMENT '创建时间',
    `update_date_time` DATETIME NOT NULL COMMENT '修改时间',
    `is_deleted`       TINYINT  NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '社团、用户、角色三表的关联表';

CREATE TABLE cl_club_image
(
    `id`               INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `club_id`          INT          NOT NULL COMMENT 'cl_club.id',
    `url`              VARCHAR(511) NOT NULL COMMENT '图片 url',
    `description`      VARCHAR(31)           DEFAULT '' COMMENT '描述',
    `create_date_time` DATETIME     NOT NULL COMMENT '创建时间',
    `update_date_time` DATETIME     NOT NULL COMMENT '修改时间',
    `is_deleted`       TINYINT      NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '社团图片表';

CREATE TABLE cl_club_activity_image
(
    `id`               INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `club_id`          INT          NOT NULL COMMENT 'cl_club.id',
    `url`              VARCHAR(511) NOT NULL COMMENT '图片 url',
    `description`      VARCHAR(31)           DEFAULT '' COMMENT '描述',
    `create_date_time` DATETIME     NOT NULL COMMENT '创建时间',
    `update_date_time` DATETIME     NOT NULL COMMENT '修改时间',
    `is_deleted`       TINYINT      NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '社团活动图片表';

CREATE TABLE cl_club_activity
(
    `id`                  INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `club_id`             INT          NOT NULL COMMENT 'cl_club.id',
    `category`            VARCHAR(15)  NOT NULL COMMENT '分类',
    `status_id`           INT          NOT NULL COMMENT '状态，cl_club_activity_status.id',
    `process_description` VARCHAR(255) NOT NULL COMMENT '流程描述',
    `start_date_time`     DATETIME     NOT NULL COMMENT '开始时间',
    `end_date_time`       DATETIME     NOT NULL COMMENT '结束时间',
    `location`            VARCHAR(31)  NOT NULL COMMENT '位置',
    `notice`              VARCHAR(255) NOT NULL COMMENT '通知',
    `grade_limit`         VARCHAR(255) NOT NULL COMMENT '年级限制',
    `faculty_limit`       VARCHAR(255) NOT NULL COMMENT '院系限制',
    `people_num_limit`    VARCHAR(255) NOT NULL COMMENT '人数限制',
    `applicants_num`      INT          NOT NULL COMMENT '已报名人数',
    `contact_information` VARCHAR(255) NOT NULL COMMENT '联系方式',
    `create_date_time`    DATETIME     NOT NULL COMMENT '创建时间',
    `update_date_time`    DATETIME     NOT NULL COMMENT '修改时间',
    `is_deleted`          TINYINT      NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '社团活动表';

CREATE TABLE cl_club_post
(
    `id`               INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `club_id`          INT           NOT NULL COMMENT '社团 id',
    `content`          VARCHAR(1023) NOT NULL COMMENT '帖子内容',
    `good_num`         INT                    DEFAULT 0 COMMENT '点赞数',
    `bad_num`          INT                    DEFAULT 0 COMMENT '点踩数',
    `create_date_time` DATETIME      NOT NULL COMMENT '创建时间',
    `update_date_time` DATETIME      NOT NULL COMMENT '修改时间',
    `is_deleted`       TINYINT       NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '社团动态帖子表';


CREATE TABLE cl_club_post_remark
(
    `id`               INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `club_post_id`     INT           NOT NULL COMMENT 'cl_club_post.id',
    `parent_id`        INT COMMENT 'cl_club_post_remark.id',
    `user_id`          INT           NOT NULL COMMENT 'per_user.id',
    `content`          VARCHAR(1023) NOT NULL COMMENT '评论内容',
    `good_num`         INT                    DEFAULT 0 COMMENT '点赞数',
    `bad_num`          INT                    DEFAULT 0 COMMENT '点踩数',
    `create_date_time` DATETIME      NOT NULL COMMENT '创建时间',
    `update_date_time` DATETIME      NOT NULL COMMENT '修改时间',
    `is_deleted`       TINYINT       NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '社团动态评论表';

CREATE TABLE cl_club_activity_status
(
    `id`               INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `name`             VARCHAR(7)  NOT NULL COMMENT '社团活动状态名称',
    `description`      VARCHAR(31) NOT NULL COMMENT '描述',
    `create_date_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_date_time` DATETIME    NOT NULL COMMENT '修改时间',
    `is_deleted`       TINYINT     NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '社团活动状态表';

/*====================================
  小游戏相关，没有前缀，
  包含：
    - mini_game
    - mini_game_remark

    ======================================*/

CREATE TABLE mini_game
(
    `id`               INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `cover_drawing`    VARCHAR(511) NOT NULL COMMENT '游戏封面图',
    `name`             VARCHAR(31)  NOT NULL COMMENT '名称',
    `requirement`      VARCHAR(511) NOT NULL COMMENT '要求',
    `introduce`        VARCHAR(511) NOT NULL COMMENT '介绍',
    `r_p_mechanism`    VARCHAR(511) NOT NULL COMMENT '奖惩机制',
    `liveness`         INT                   DEFAULT 100 COMMENT '活跃度',
    `score`            INT                   DEFAULT 50 COMMENT '评分',
    `create_date_time` DATETIME     NOT NULL COMMENT '创建时间',
    `update_date_time` DATETIME     NOT NULL COMMENT '修改时间',
    `is_deleted`       TINYINT      NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '小游戏信息表';

CREATE TABLE mini_game_remark
(
    `id`               INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `game_id`          INT           NOT NULL COMMENT 'mini_game.id',
    `parent_id`        INT COMMENT 'mini_game_remark.id',
    `user_id`          INT           NOT NULL COMMENT 'per_user.id',
    `content`          VARCHAR(1023) NOT NULL COMMENT '评论内容',
    `good_num`         INT                    DEFAULT 0 COMMENT '点赞数',
    `bad_num`          INT                    DEFAULT 0 COMMENT '点踩数',
    `create_date_time` DATETIME      NOT NULL COMMENT '创建时间',
    `update_date_time` DATETIME      NOT NULL COMMENT '修改时间',
    `is_deleted`       TINYINT       NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '小游戏评论表';

/*====================================
  线上聊天平台相关，没有前缀，
  包含：
    - online_chat_record

    ======================================*/

CREATE TABLE online_chat_record
(
    `id`               INT PRIMARY KEY AUTO_INCREMENT COMMENT '自增主键 id，无意义',
    `sender_id`        INT          NOT NULL COMMENT '发送者，per_user.id',
    `getter_id`        INT          NOT NULL COMMENT '接收者，per_user.id',
    `club_activity_id` INT          NOT NULL COMMENT '活动，cl_club_activity.id',
    `content`          VARCHAR(511) NOT NULL COMMENT '消息内容',
    `create_date_time` DATETIME     NOT NULL COMMENT '创建时间',
    `update_date_time` DATETIME     NOT NULL COMMENT '修改时间',
    `is_deleted`       TINYINT      NOT NULL DEFAULT 0 COMMENT '是否已删除。用于逻辑删除，默认值为 0 表示未删除，1 表示已删除'
) COMMENT '线上聊天平台的聊天记录表';
