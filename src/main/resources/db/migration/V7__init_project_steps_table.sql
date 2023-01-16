    create table project_steps
    (
    id int primary key auto_increment,
    description varchar(100) not null,
    project_id int not null,
    days_to_dead_line int not null
    );
    alter table project_steps
    add foreign key (project_id) references projects(id);