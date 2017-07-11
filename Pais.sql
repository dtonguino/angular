-- Create table
create table GEN_PAIS
(
  cod_pais VARCHAR2(15) not null,
  nombre   VARCHAR2(250) not null
);
-- Add comments to the table 
comment on table GEN_PAIS
  is 'Almacena la información de los países';
-- Add comments to the columns 
comment on column GEN_PAIS.cod_pais
  is 'El código del país';
comment on column GEN_PAIS.nombre
  is 'El nombre del país';
-- Create/Recreate primary, unique and foreign key constraints 
alter table GEN_PAIS
  add constraint PK_GEN_PAIS primary key (COD_PAIS);

insert into GEN_PAIS (COD_PAIS, NOMBRE)
values ('ECU', 'Ecuador');

insert into GEN_PAIS (COD_PAIS, NOMBRE)
values ('PER', 'Peru');