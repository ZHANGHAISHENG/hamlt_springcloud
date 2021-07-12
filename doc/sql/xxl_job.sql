create database if not exists  xxl_job;

create table xxl_job_group
(
    id           int auto_increment
        primary key,
    app_name     varchar(64)       not null comment '执行器AppName',
    title        varchar(12)       not null comment '执行器名称',
    `order`      int     default 0 not null comment '排序',
    address_type tinyint default 0 not null comment '执行器地址类型：0=自动注册、1=手动录入',
    address_list varchar(512)      null comment '执行器地址列表，多地址逗号分隔'
)
    charset = utf8;

INSERT INTO xxl_job.xxl_job_group (id, app_name, title, `order`, address_type, address_list) VALUES (2, 'job-test', '测试job', 1, 0, null);
create table xxl_job_info
(
    id                        int auto_increment
        primary key,
    job_group                 int               not null comment '执行器主键ID',
    job_cron                  varchar(128)      not null comment '任务执行CRON',
    job_desc                  varchar(255)      not null,
    add_time                  datetime          null,
    update_time               datetime          null,
    author                    varchar(64)       null comment '作者',
    alarm_email               varchar(255)      null comment '报警邮件',
    executor_route_strategy   varchar(50)       null comment '执行器路由策略',
    executor_handler          varchar(255)      null comment '执行器任务handler',
    executor_param            varchar(512)      null comment '执行器任务参数',
    executor_block_strategy   varchar(50)       null comment '阻塞处理策略',
    executor_timeout          int     default 0 not null comment '任务执行超时时间，单位秒',
    executor_fail_retry_count int     default 0 not null comment '失败重试次数',
    glue_type                 varchar(50)       not null comment 'GLUE类型',
    glue_source               mediumtext        null comment 'GLUE源代码',
    glue_remark               varchar(128)      null comment 'GLUE备注',
    glue_updatetime           datetime          null comment 'GLUE更新时间',
    child_jobid               varchar(255)      null comment '子任务ID，多个逗号分隔',
    trigger_status            tinyint default 0 not null comment '调度状态：0-停止，1-运行',
    trigger_last_time         bigint  default 0 not null comment '上次调度时间',
    trigger_next_time         bigint  default 0 not null comment '下次调度时间'
)
    charset = utf8;

INSERT INTO xxl_job.xxl_job_info (id, job_group, job_cron, job_desc, add_time, update_time, author, alarm_email, executor_route_strategy, executor_handler, executor_param, executor_block_strategy, executor_timeout, executor_fail_retry_count, glue_type, glue_source, glue_remark, glue_updatetime, child_jobid, trigger_status, trigger_last_time, trigger_next_time) VALUES (13, 2, '0 40 12,18 * * ?', 'test', '2021-07-01 14:05:01', '2021-07-01 14:05:06', '张海生', '', 'FIRST', 'jobTestHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2021-07-01 14:05:01', '', 1, 0, 1625136000000);
create table xxl_job_lock
(
    lock_name varchar(50) not null comment '锁名称'
        primary key
)
    charset = utf8;

INSERT INTO xxl_job.xxl_job_lock (lock_name) VALUES ('schedule_lock');
create table xxl_job_log
(
    id                        bigint auto_increment
        primary key,
    job_group                 int               not null comment '执行器主键ID',
    job_id                    int               not null comment '任务，主键ID',
    executor_address          varchar(255)      null comment '执行器地址，本次执行的地址',
    executor_handler          varchar(255)      null comment '执行器任务handler',
    executor_param            varchar(512)      null comment '执行器任务参数',
    executor_sharding_param   varchar(20)       null comment '执行器任务分片参数，格式如 1/2',
    executor_fail_retry_count int     default 0 not null comment '失败重试次数',
    trigger_time              datetime          null comment '调度-时间',
    trigger_code              int               not null comment '调度-结果',
    trigger_msg               text              null comment '调度-日志',
    handle_time               datetime          null comment '执行-时间',
    handle_code               int               not null comment '执行-状态',
    handle_msg                text              null comment '执行-日志',
    alarm_status              tinyint default 0 not null comment '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败'
)
    charset = utf8;

create index I_handle_code
    on xxl_job_log (handle_code);

create index I_trigger_time
    on xxl_job_log (trigger_time);

INSERT INTO xxl_job.xxl_job_log (id, job_group, job_id, executor_address, executor_handler, executor_param, executor_sharding_param, executor_fail_retry_count, trigger_time, trigger_code, trigger_msg, handle_time, handle_code, handle_msg, alarm_status) VALUES (3, 2, 13, '10.150.129.28:8885', 'jobTestHandler', '', null, 0, '2021-07-01 14:05:11', 500, '任务触发类型：手动触发<br>调度机器：10.150.129.28<br>执行器-注册方式：自动注册<br>执行器-地址列表：[10.150.129.28:8885]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style="color:#00c0ef;" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：10.150.129.28:8885<br>code：500<br>msg：com.xxl.rpc.util.XxlRpcException: The access token[] is wrong.
	at com.xxl.rpc.remoting.invoker.reference.XxlRpcReferenceBean$1.invoke(XxlRpcReferenceBean.java:221)
	at com.sun.proxy.$Proxy99.run(Unknown Source)
	at com.xxl.job.admin.core.trigger.XxlJobTrigger.runExecutor(XxlJobTrigger.java:196)
	at com.xxl.job.admin.core.trigger.XxlJobTrigger.processTrigger(XxlJobTrigger.java:149)
	at com.xxl.job.admin.core.trigger.XxlJobTrigger.trigger(XxlJobTrigger.java:74)
	at com.xxl.job.admin.core.thread.JobTriggerPoolHelper$3.run(JobTriggerPoolHelper.java:76)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
', null, 0, null, 1);
INSERT INTO xxl_job.xxl_job_log (id, job_group, job_id, executor_address, executor_handler, executor_param, executor_sharding_param, executor_fail_retry_count, trigger_time, trigger_code, trigger_msg, handle_time, handle_code, handle_msg, alarm_status) VALUES (4, 2, 13, '10.150.129.28:8885', 'jobTestHandler', '', null, 0, '2021-07-01 14:07:56', 200, '任务触发类型：手动触发<br>调度机器：10.150.129.28<br>执行器-注册方式：自动注册<br>执行器-地址列表：[10.150.129.28:8885]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style="color:#00c0ef;" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：10.150.129.28:8885<br>code：200<br>msg：null', '2021-07-01 14:07:56', 200, '', 0);
create table xxl_job_logglue
(
    id          int auto_increment
        primary key,
    job_id      int          not null comment '任务，主键ID',
    glue_type   varchar(50)  null comment 'GLUE类型',
    glue_source mediumtext   null comment 'GLUE源代码',
    glue_remark varchar(128) not null comment 'GLUE备注',
    add_time    timestamp    null,
    update_time timestamp    null on update CURRENT_TIMESTAMP
)
    charset = utf8;


create table xxl_job_registry
(
    id             int auto_increment
        primary key,
    registry_group varchar(255)                        not null,
    registry_key   varchar(255)                        not null,
    registry_value varchar(255)                        not null,
    update_time    timestamp default CURRENT_TIMESTAMP not null
)
    charset = utf8;

create index i_g_k_v
    on xxl_job_registry (registry_group, registry_key, registry_value);


create table xxl_job_user
(
    id         int auto_increment
        primary key,
    username   varchar(50)  not null comment '账号',
    password   varchar(50)  not null comment '密码',
    role       tinyint      not null comment '角色：0-普通用户、1-管理员',
    permission varchar(255) null comment '权限：执行器ID列表，多个逗号分割',
    constraint i_username
        unique (username)
)
    charset = utf8;

INSERT INTO xxl_job.xxl_job_user (id, username, password, role, permission) VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, null);