    create table note_groups
    (
    id int primary key auto_increment,
    description varchar(100) not null,
    done bit
    );
        alter table note add column note_group_id int null;
        alter table note add foreign key (note_group_id) references note_groups(id);