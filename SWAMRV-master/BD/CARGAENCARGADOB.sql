--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure CARGAENCARGADOB
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."CARGAENCARGADOB" (
v_rut IN encargado_bodega.rut_encargado%type,
v_nombre OUT encargado_bodega.nombre%type,
v_contrasena OUT encargado_bodega.contrasena%type,
v_apepa OUT encargado_bodega.apellido_paterno%type,
v_apema OUT encargado_bodega.apellido_materno%type,
v_dire OUT encargado_bodega.direccion%type,
v_fono OUT encargado_bodega.telefono%type,
v_email OUT encargado_bodega.email%type,
v_comuna OUT encargado_bodega.id_comuna%type,
v_fechain OUT encargado_bodega.fecha_ingreso%type,
v_fechana OUT encargado_bodega.fecha_nacimiento%type,
v_detalle OUT encargado_bodega.detalle%type,
v_foto OUT encargado_bodega.foto%type)
as

BEGIN
SELECT nombre, contrasena, apellido_paterno, apellido_materno, direccion, telefono, email, id_comuna, fecha_ingreso, fecha_nacimiento, detalle, foto
INTO v_nombre, v_contrasena, v_apepa, v_apema, v_dire, v_fono, v_email, v_comuna, v_fechain, v_fechana, v_detalle, v_foto
FROM ENCARGADO_BODEGA WHERE rut_encargado = v_rut;
commit;

exception
WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('RUT '||v_rut||' no encontrado');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al cargar el administrador '||v_rut||'. Operacion interrumpida.');
rollback;
end;

/
