    create table projects
    (
    id int primary key auto_increment,
    description varchar(100) not null
    );
        alter table note_groups add column project_id int null;
        alter table note_groups add foreign key (project_id) references projects(id);