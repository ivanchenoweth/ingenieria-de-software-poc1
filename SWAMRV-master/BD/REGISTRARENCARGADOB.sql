--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure REGISTRARENCARGADOB
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."REGISTRARENCARGADOB" (
v_rut encargado_bodega.rut_encargado%type,
v_nombre encargado_bodega.nombre%type,
v_contrasena encargado_bodega.contrasena%type,
v_apepa encargado_bodega.apellido_paterno%type,
v_apema encargado_bodega.apellido_materno%type,
v_dire encargado_bodega.direccion%type,
v_tele encargado_bodega.telefono%type,
v_email encargado_bodega.email%type,
v_id_comuna encargado_bodega.id_comuna%type,
v_fechain encargado_bodega.fecha_ingreso%type,
v_fechana encargado_bodega.fecha_nacimiento%type,
v_detall encargado_bodega.detalle%type,
v_foto encargado_bodega.foto%type)

is
BEGIN
INSERT INTO ENCARGADO_BODEGA(rut_encargado, nombre, contrasena, apellido_paterno,apellido_materno, direccion, telefono, email, id_comuna, fecha_ingreso, fecha_nacimiento, detalle, foto) 
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
