--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure CARGAADMINISTRADORES
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."CARGAADMINISTRADORES" (
v_rut IN administrador.rut_administrador%type,
v_nombre OUT administrador.nombre%type,
v_contrasena OUT administrador.contrasena%type,
v_apepa OUT  administrador.apellido_paterno%type,
v_apema OUT  administrador.apellido_materno%type,
v_dire OUT administrador.direccion%type,
v_fono OUT administrador.telefono%type,
v_email OUT administrador.email%type,
v_comuna OUT administrador.id_comuna%type,
v_fechain OUT administrador.fecha_ingreso%type,
v_fechana OUT administrador.fecha_nacimiento%type,
v_detalle OUT administrador.detalle%type,
v_foto OUT administrador.foto%type)
as

BEGIN
SELECT nombre, contrasena, apellido_paterno, apellido_materno, direccion, telefono, email, id_comuna, fecha_ingreso, fecha_nacimiento, detalle, foto
INTO v_nombre, v_contrasena, v_apepa, v_apema, v_dire, v_fono, v_email, v_comuna, v_fechain, v_fechana, v_detalle, v_foto
FROM ADMINISTRADOR WHERE rut_administrador = v_rut;
commit;

exception
WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('RUT '||v_rut||' no encontrado');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al cargar el administrador '||v_rut||'. Operacion interrumpida.');
rollback;
end;

/
