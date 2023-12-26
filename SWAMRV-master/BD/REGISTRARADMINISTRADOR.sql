--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure REGISTRARADMINISTRADOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."REGISTRARADMINISTRADOR" (
v_rut administrador.rut_administrador%type,
v_nombre administrador.nombre%type,
v_contrasena administrador.contrasena%type,
v_apepa administrador.apellido_paterno%type,
v_apema administrador.apellido_materno%type,
v_dire administrador.direccion%type,
v_tele administrador.telefono%type,
v_email administrador.email%type,
v_id_comuna administrador.id_comuna%type,
v_fechain administrador.fecha_ingreso%type,
v_fechana administrador.fecha_nacimiento%type,
v_detall administrador.detalle%type,
v_foto administrador.foto%type)

is
BEGIN
INSERT INTO ADMINISTRADOR(rut_administrador, nombre, contrasena, apellido_paterno,apellido_materno, direccion, telefono, email, id_comuna, fecha_ingreso, fecha_nacimiento, detalle, foto) 
VALUES (v_rut, v_nombre, v_contrasena, v_apepa, v_apema, v_dire, v_tele, v_email, v_id_comuna, v_fechain, v_fechana, v_detall, v_foto);

commit;
exception
WHEN DUP_VAL_ON_INDEX then
DBMS_OUTPUT.PUT_LINE ('RUT Ya existe en la Base de Datos');

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

/*WHEN e_personalRegistrado then
RAISE_APPLICATION_ERROR (-20100, 'Conductor '||v_rut||' no esta disponible');*/
WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al registrar al Conductor '||v_rut||'. Operacion interrumpida.');
rollback;
end;

/
