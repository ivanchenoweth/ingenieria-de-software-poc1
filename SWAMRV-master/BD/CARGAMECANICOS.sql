--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure CARGAMECANICOS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."CARGAMECANICOS" (
v_rut IN mecanico.rut_mecanico%type,
v_nombre OUT mecanico.nombre%type,
v_apepa OUT  mecanico.apellido_paterno%type,
v_apema OUT  mecanico.apellido_materno%type,
v_dire OUT mecanico.direccion%type,
v_fono OUT mecanico.telefono%type,
v_email OUT mecanico.email%type,
v_comuna OUT mecanico.id_comuna%type,
v_fechain OUT mecanico.fecha_ingreso%type,
v_fechana OUT mecanico.fecha_nacimiento%type,
v_detalle OUT mecanico.detalle%type,
v_foto OUT mecanico.foto%type)
as

BEGIN
SELECT nombre, apellido_paterno, apellido_materno, direccion, telefono, email, id_comuna, fecha_ingreso, fecha_nacimiento, detalle, foto
INTO v_nombre, v_apepa, v_apema, v_dire, v_fono, v_email, v_comuna, v_fechain, v_fechana, v_detalle, v_foto
FROM MECANICO WHERE rut_mecanico = v_rut;
commit;

exception
WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('RUT '||v_rut||' no encontrado');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al cargar el administrador '||v_rut||'. Operacion interrumpida.');
rollback;
end;

/
