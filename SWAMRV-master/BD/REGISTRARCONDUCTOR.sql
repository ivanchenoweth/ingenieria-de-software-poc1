--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure REGISTRARCONDUCTOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."REGISTRARCONDUCTOR" (
v_rut conductor.rut_conductor%type,
v_nombre conductor.nombre%type,
v_apepa conductor.apellido_paterno%type,
v_apema conductor.apellido_materno%type,
v_dire conductor.direccion%type,
v_tele conductor.telefono%type,
v_email conductor.email%type,
v_id_comuna conductor.id_comuna%type,
v_fechain conductor.fecha_ingreso%type,
v_fechana conductor.fecha_nacimiento%type,
v_radio conductor.radio%type,
v_licen conductor.licencia%type,
v_detall conductor.detalle%type,
v_foto conductor.foto%type)

is
BEGIN
INSERT INTO CONDUCTOR(rut_conductor,nombre,apellido_paterno,apellido_materno, direccion, telefono, email, id_comuna, fecha_ingreso, fecha_nacimiento, radio, licencia, detalle, foto) 
VALUES (v_rut, v_nombre, v_apepa, v_apema, v_dire, v_tele, v_email, v_id_comuna, v_fechain, v_fechana, v_radio, v_licen, v_detall, v_foto);

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
