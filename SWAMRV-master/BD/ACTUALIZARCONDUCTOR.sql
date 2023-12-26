--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure ACTUALIZARCONDUCTOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."ACTUALIZARCONDUCTOR" (
v_rut conductor.rut_conductor%type,
v_nombre conductor.nombre%type,
v_apepa conductor.apellido_paterno%type,
v_apema conductor.apellido_materno%type,
v_dire conductor.direccion%type,
v_tele conductor.telefono%type,
v_email conductor.email%type,
v_id_comuna conductor.id_comuna%type,
v_fechana conductor.fecha_nacimiento%type,
v_radio conductor.radio%type,
v_licen conductor.licencia%type,
v_detall conductor.detalle%type,
v_foto conductor.foto%type)

is
BEGIN
UPDATE CONDUCTOR
SET nombre = v_nombre,
apellido_paterno = v_apepa,
apellido_materno = v_apema,
direccion = v_dire,
telefono = v_tele,
email = v_email,
id_comuna = v_id_comuna,
fecha_nacimiento = v_fechana,
radio = v_radio,
licencia = v_licen,
detalle = v_detall,
foto = v_foto
WHERE rut_conductor = v_rut;

commit;
exception
WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al actualizar al Conductor '||v_rut||'. Operacion interrumpida.');
rollback;
end;

/
