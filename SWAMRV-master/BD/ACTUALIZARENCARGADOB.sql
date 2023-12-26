--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure ACTUALIZARENCARGADOB
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."ACTUALIZARENCARGADOB" (
v_rut encargado_bodega.rut_encargado%type,
v_nombre encargado_bodega.nombre%type,
v_contrasena encargado_bodega.contrasena%type,
v_apepa encargado_bodega.apellido_paterno%type,
v_apema encargado_bodega.apellido_materno%type,
v_dire encargado_bodega.direccion%type,
v_tele encargado_bodega.telefono%type,
v_email encargado_bodega.email%type,
v_id_comuna encargado_bodega.id_comuna%type,
v_fechana encargado_bodega.fecha_nacimiento%type,
v_detall encargado_bodega.detalle%type,
v_foto encargado_bodega.foto%type)

is
BEGIN
UPDATE ENCARGADO_BODEGA
SET nombre = v_nombre,
contrasena = v_contrasena,
apellido_paterno = v_apepa,
apellido_materno = v_apema,
direccion = v_dire,
telefono = v_tele,
email = v_email,
id_comuna = v_id_comuna,
fecha_nacimiento = v_fechana,
detalle = v_detall,
foto = v_foto
WHERE rut_encargado = v_rut;

commit;
exception
WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al actualizar al Encargado Bodega '||v_rut||'. Operacion interrumpida.');
rollback;
end;

/
