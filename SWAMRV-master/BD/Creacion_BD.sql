/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     26-03-2012 12:20:31                          */
/*==============================================================*/


alter table ADMINISTRADOR
   drop constraint FK_ADMINIST_REFERENCE_COMUNA;

alter table COMPRA
   drop constraint FK_COMPRA_REFERENCE_PROVEEDO;

alter table COMPRA
   drop constraint FK_COMPRA_REFERENCE_ADMINIST;

alter table COMPRA_DETALLE
   drop constraint FK_COMPRA_D_REFERENCE_REPUESTO;

alter table COMPRA_DETALLE
   drop constraint FK_COMPRA_D_REFERENCE_COMPRA;

alter table CONDUCTOR
   drop constraint FK_CONDUCTO_REFERENCE_COMUNA;

alter table MECANICO
   drop constraint FK_MECANICO_REFERENCE_COMUNA;

alter table ORDEN_TRABAJO
   drop constraint FK_ORDEN_TR_REFERENCE_ADMINIST;

alter table ORDEN_TRABAJO
   drop constraint FK_ORDEN_TR_REFERENCE_MECANICO;

alter table ORDEN_TRABAJO
   drop constraint FK_ORDEN_TR_REFERENCE_VEHICULO;

alter table ORDEN_TRABAJO_DETALLE
   drop constraint FK_ORDEN_TR_REFERENCE_FALLA;

alter table ORDEN_TRABAJO_DETALLE
   drop constraint FK_ORDEN_TR_REFERENCE_REPUESTO;

alter table ORDEN_TRABAJO_DETALLE
   drop constraint FK_ORDEN_TR_REFERENCE_VEH;

alter table ORDEN_TRABAJO_DETALLE
   drop constraint FK_ORDEN_TR_REFERENCE_SERVICIO;

alter table PLAN_TRABAJO_DETALLE
   drop constraint FK_PLAN_TRA_REFERENCE_ORDEN_TR;

alter table PLAN_TRABAJO_DETALLE
   drop constraint FK_PLAN_TRA_REFERENCE_PLANTRAB;

alter table PROVEEDOR
   drop constraint FK_PROVEEDO_REFERENCE_COMUNA;

alter table VEHICULO_CONDUCTOR
   drop constraint FK_VEHICULO_REFERENCE_VC;

alter table VEHICULO_CONDUCTOR
   drop constraint FK_VEHICULO_REFERENCE_CONDUCTO;

drop table ADMINISTRADOR cascade constraints;

drop table COMPRA cascade constraints;

drop table COMPRA_DETALLE cascade constraints;

drop table COMUNA cascade constraints;

drop table CONDUCTOR cascade constraints;

drop table FALLA cascade constraints;

drop table MECANICO cascade constraints;

drop table ORDEN_TRABAJO cascade constraints;

drop table ORDEN_TRABAJO_DETALLE cascade constraints;

drop table PLANTRABAJO cascade constraints;

drop table PLAN_TRABAJO_DETALLE cascade constraints;

drop table PROVEEDOR cascade constraints;

drop table REPUESTO cascade constraints;

drop table SERVICIO cascade constraints;

drop table VEHICULO cascade constraints;

drop table VEHICULO_CONDUCTOR cascade constraints;

/*==============================================================*/
/* Table: ADMINISTRADOR                                         */
/*==============================================================*/
create table ADMINISTRADOR  (
   RUT_ADMINISTRADOR    VARCHAR2(12)                    not null,
   CONTRASENA           VARCHAR2(40),
   NOMBRE               VARCHAR2(30),
   APELLIDO_PATERNO     VARCHAR2(30),
   APELLIDO_MATERNO     VARCHAR2(30),
   DIRECCION            VARCHAR150,
   TELEFONO             NUMBER,
   EMAIL                VARCHAR2(30),
   ID_COMUNA            NUMBER,
   FECHA_INGRESO        DATE,
   FECHA_RETIRO         DATE,
   FECHA_NACIMIENTO     DATE,
   DETALLE              VARCHAR2(150),
   FOTO                 BLOB,
   constraint PK_ADMINISTRADOR primary key (RUT_ADMINISTRADOR)
);

/*==============================================================*/
/* Table: COMPRA                                                */
/*==============================================================*/
create table COMPRA  (
   NRO_FACTURA          NUMBER                          not null,
   FECHA_COMPRA         DATE                            not null,
   ID_PROVEEDOR         NUMBER                          not null,
   RUT_ADMINISTRADOR    VARCHAR2(12),
   DETALLE              VARCHAR2(150),
   constraint PK_COMPRA primary key (NRO_FACTURA)
);

/*==============================================================*/
/* Table: COMPRA_DETALLE                                        */
/*==============================================================*/
create table COMPRA_DETALLE  (
   NRO_FACTURA          NUMBER                          not null,
   ID_REPUESTO          NUMBER                          not null,
   CANTIDAD             NUMBER,
   DETALLE              VARCHAR2(150),
   constraint PK_COMPRA_DETALLE primary key (NRO_FACTURA, ID_REPUESTO)
);

/*==============================================================*/
/* Table: COMUNA                                                */
/*==============================================================*/
create table COMUNA  (
   ID_COMUNA            NUMBER                          not null,
   NOMBRE               VARCHAR2(30),
   DESCRIPCION          VARCHAR2(150),
   constraint PK_COMUNA primary key (ID_COMUNA)
);

/*==============================================================*/
/* Table: CONDUCTOR                                             */
/*==============================================================*/
create table CONDUCTOR  (
   RUT_CONDUCTOR        VARCHAR2(12)                    not null,
   CONTRASENA           VARCHAR2(40),
   NOMBRE               VARCHAR2(30),
   APELLIDO_PATERNO     VARCHAR2(30),
   APELLIDO_MATERNO     VARCHAR2(30),
   DIRECCION            VARCHAR150,
   TELEFONO             NUMBER,
   EMAIL                VARCHAR2(30),
   ID_COMUNA            NUMBER,
   FECHA_INGRESO        DATE,
   FECHA_RETIRO         DATE,
   FECHA_NACIMIENTO     DATE,
   RADIO                NUMBER,
   LICENCIA             CHAR(10),
   DETALLE              VARCHAR2(150),
   FOTO                 BLOB,
   constraint PK_CONDUCTOR primary key (RUT_CONDUCTOR)
);

/*==============================================================*/
/* Table: FALLA                                                 */
/*==============================================================*/
create table FALLA  (
   ID_FALLA             NUMBER                          not null,
   NOMBRE               VARCHAR2(30),
   DESCRIPCION          VARCHAR2(150),
   constraint PK_FALLA primary key (ID_FALLA)
);

/*==============================================================*/
/* Table: MECANICO                                              */
/*==============================================================*/
create table MECANICO  (
   RUT_MECANICO         VARCHAR2(12)                    not null,
   CONTRASENA           VARCHAR2(40),
   NOMBRE               VARCHAR2(30),
   APELLIDO_PATERNO     VARCHAR2(30),
   APELLIDO_MATERNO     VARCHAR2(30),
   DIRECCION            VARCHAR150,
   TELEFONO             NUMBER,
   EMAIL                VARCHAR2(30),
   ID_COMUNA            NUMBER,
   FECHA_INGRESO        DATE,
   FECHA_RETIRO         DATE,
   FECHA_NACIMIENTO     DATE,
   DETALLE              VARCHAR2(150),
   FOTO                 BLOB,
   constraint PK_MECANICO primary key (RUT_MECANICO)
);

/*==============================================================*/
/* Table: ORDEN_TRABAJO                                         */
/*==============================================================*/
create table ORDEN_TRABAJO  (
   ID_OT                NUMBER                          not null,
   PATENTE              VARCHAR2(6)                     not null,
   RUT_ADMINISTRADOR    VARCHAR2(12),
   RUT_MECANICO         VARCHAR2(12),
   FECHA_INICIO         DATE,
   FECHA_TERMINO        DATE,
   TIPO_TRABAJO         VARCHAR2(20),
   constraint PK_ORDEN_TRABAJO primary key (ID_OT, PATENTE)
);

/*==============================================================*/
/* Table: ORDEN_TRABAJO_DETALLE                                 */
/*==============================================================*/
create table ORDEN_TRABAJO_DETALLE  (
   ID_OT                NUMBER                          not null,
   PATENTE              VARCHAR2(6)                     not null,
   ID_REPUESTO          NUMBER                          not null,
   CANTIDAD             NUMBER,
   ID_FALLA             NUMBER                          not null,
   ID_SERVICIO          NUMBER                          not null,
   OBSERVACIONES        VARCHAR2(150),
   constraint PK_ORDEN_TRABAJO_DETALLE primary key (ID_OT, PATENTE, ID_FALLA, ID_REPUESTO, ID_SERVICIO)
);

/*==============================================================*/
/* Table: PLANTRABAJO                                           */
/*==============================================================*/
create table PLANTRABAJO  (
   ID_PLAN              NUMBER                          not null,
   NOMBRE               VARCHAR2(30),
   DESCRIPCION          VARCHAR2(150),
   constraint PK_PLANTRABAJO primary key (ID_PLAN)
);

/*==============================================================*/
/* Table: PLAN_TRABAJO_DETALLE                                  */
/*==============================================================*/
create table PLAN_TRABAJO_DETALLE  (
   ID_PLAN              NUMBER                          not null,
   ID_OT                NUMBER                          not null,
   PATENTE              VARCHAR2(6)                     not null,
   ID_FALLA             NUMBER                          not null,
   ID_REPUESTO          NUMBER                          not null,
   ID_SERVICIO          NUMBER                          not null,
   FECHA                DATE,
   COMBUSTIBLE          NUMBER,
   ACEITE               NUMBER,
   KILOMETRAJE_ACTUAL   NUMBER,
   KILOMETRAJE_TOTAL    NUMBER,
   FRENOS               NUMBER,
   BATERIA              NUMBER,
   LLANTAS              NUMBER,
   SUSPENSION           NUMBER,
   AMORTIGUADORES       NUMBER,
   RODAMIENTO           NUMBER,
   COLISIONES           NUMBER,
   CORREA_DISTR         NUMBER,
   OBSERVACIONES        VARCHAR2(150),
   constraint PK_PLAN_TRABAJO_DETALLE primary key (ID_PLAN, ID_OT, PATENTE, ID_FALLA, ID_REPUESTO, ID_SERVICIO)
);

/*==============================================================*/
/* Table: PROVEEDOR                                             */
/*==============================================================*/
create table PROVEEDOR  (
   ID_PROVEEDOR         NUMBER                          not null,
   NOMBRE               VARCHAR2(30),
   DIRECCION            VARCHAR2(150),
   EMAIL                VARCHAR2(30),
   TELEFONO             NUMBER,
   DESCRIPCION          VARCHAR2(150),
   ID_COMUNA            NUMBER,
   constraint PK_PROVEEDOR primary key (ID_PROVEEDOR)
);

/*==============================================================*/
/* Table: REPUESTO                                              */
/*==============================================================*/
create table REPUESTO  (
   ID_REPUESTO          NUMBER                          not null,
   NOMBRE               VARCHAR2(30),
   DESCRIPCION          VARCHAR2(150),
   CANTIDAD             NUMBER,
   FOTO                 BLOB,
   constraint PK_REPUESTO primary key (ID_REPUESTO)
);

/*==============================================================*/
/* Table: SERVICIO                                              */
/*==============================================================*/
create table SERVICIO  (
   ID_SERVICIO          NUMBER                          not null,
   NOMBRE               VARCHAR2(30),
   DESCRIPCION          VARCHAR2(150),
   constraint PK_SERVICIO primary key (ID_SERVICIO)
);

/*==============================================================*/
/* Table: VEHICULO                                              */
/*==============================================================*/
create table VEHICULO  (
   PATENTE              VARCHAR2(6)                     not null,
   CHASIS               VARCHAR2(10),
   COLOR                VARCHAR2(10),
   MARCA                VARCHAR2(10),
   MODELO               VARCHAR2(10),
   FECHA_INGRESO        DATE,
   FECHA_RETIRO         DATE,
   FOTO                 BLOB,
   constraint PK_VEHICULO primary key (PATENTE)
);

/*==============================================================*/
/* Table: VEHICULO_CONDUCTOR                                    */
/*==============================================================*/
create table VEHICULO_CONDUCTOR  (
   RUT_CONDUCTOR        VARCHAR2(12)                    not null,
   PATENTE              VARCHAR2(6)                     not null,
   DESCRIPCION          VARCHAR2(150),
   FECHA_ASIGNACION     DATE                            not null,
   constraint PK_VEHICULO_CONDUCTOR primary key (RUT_CONDUCTOR, PATENTE, FECHA_ASIGNACION)
);

alter table ADMINISTRADOR
   add constraint FK_ADMINIST_REFERENCE_COMUNA foreign key (ID_COMUNA)
      references COMUNA (ID_COMUNA);

alter table COMPRA
   add constraint FK_COMPRA_REFERENCE_PROVEEDO foreign key (ID_PROVEEDOR)
      references PROVEEDOR (ID_PROVEEDOR);

alter table COMPRA
   add constraint FK_COMPRA_REFERENCE_ADMINIST foreign key (RUT_ADMINISTRADOR)
      references ADMINISTRADOR (RUT_ADMINISTRADOR);

alter table COMPRA_DETALLE
   add constraint FK_COMPRA_D_REFERENCE_REPUESTO foreign key (ID_REPUESTO)
      references REPUESTO (ID_REPUESTO);

alter table COMPRA_DETALLE
   add constraint FK_COMPRA_D_REFERENCE_COMPRA foreign key (NRO_FACTURA)
      references COMPRA (NRO_FACTURA);

alter table CONDUCTOR
   add constraint FK_CONDUCTO_REFERENCE_COMUNA foreign key (ID_COMUNA)
      references COMUNA (ID_COMUNA);

alter table MECANICO
   add constraint FK_MECANICO_REFERENCE_COMUNA foreign key (ID_COMUNA)
      references COMUNA (ID_COMUNA);

alter table ORDEN_TRABAJO
   add constraint FK_ORDEN_TR_REFERENCE_ADMINIST foreign key (RUT_ADMINISTRADOR)
      references ADMINISTRADOR (RUT_ADMINISTRADOR);

alter table ORDEN_TRABAJO
   add constraint FK_ORDEN_TR_REFERENCE_MECANICO foreign key (RUT_MECANICO)
      references MECANICO (RUT_MECANICO);

alter table ORDEN_TRABAJO
   add constraint FK_ORDEN_TR_REFERENCE_VEHICULO foreign key (PATENTE)
      references VEHICULO (PATENTE);

alter table ORDEN_TRABAJO_DETALLE
   add constraint FK_ORDEN_TR_REFERENCE_FALLA foreign key (ID_FALLA)
      references FALLA (ID_FALLA);

alter table ORDEN_TRABAJO_DETALLE
   add constraint FK_ORDEN_TR_REFERENCE_REPUESTO foreign key (ID_REPUESTO)
      references REPUESTO (ID_REPUESTO);

alter table ORDEN_TRABAJO_DETALLE
   add constraint FK_ORDEN_TR_REFERENCE_VEH foreign key (PATENTE)
      references VEHICULO (PATENTE);

alter table ORDEN_TRABAJO_DETALLE
   add constraint FK_ORDEN_TR_REFERENCE_SERVICIO foreign key (ID_SERVICIO)
      references SERVICIO (ID_SERVICIO);

alter table PLAN_TRABAJO_DETALLE
   add constraint FK_PLAN_TRA_REFERENCE_ORDEN_TR foreign key (ID_OT, PATENTE, ID_FALLA, ID_REPUESTO, ID_SERVICIO)
      references ORDEN_TRABAJO_DETALLE (ID_OT, PATENTE, ID_FALLA, ID_REPUESTO, ID_SERVICIO);

alter table PLAN_TRABAJO_DETALLE
   add constraint FK_PLAN_TRA_REFERENCE_PLANTRAB foreign key (ID_PLAN)
      references PLANTRABAJO (ID_PLAN);

alter table PROVEEDOR
   add constraint FK_PROVEEDO_REFERENCE_COMUNA foreign key (ID_COMUNA)
      references COMUNA (ID_COMUNA);

alter table VEHICULO_CONDUCTOR
   add constraint FK_VEHICULO_REFERENCE_VC foreign key (PATENTE)
      references VEHICULO (PATENTE);

alter table VEHICULO_CONDUCTOR
   add constraint FK_VEHICULO_REFERENCE_CONDUCTO foreign key (RUT_CONDUCTOR)
      references CONDUCTOR (RUT_CONDUCTOR);

