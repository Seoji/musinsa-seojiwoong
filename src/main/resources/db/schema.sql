create table musinsaCategory
(
    id          int auto_increment
        primary key,
    pid         int                                null,
    text        varchar(100)                       not null,
    createdDate datetime default CURRENT_TIMESTAMP null,
    updatedDate datetime                           null on update CURRENT_TIMESTAMP,
    constraint FKCategoryPidToCategoryId
        foreign key (pid) references musinsaCategory (id)
            on delete cascade
);
