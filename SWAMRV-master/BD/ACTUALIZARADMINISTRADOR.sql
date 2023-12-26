--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure ACTUALIZARADMINISTRADOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."ACTUALIZARADMINISTRADOR" (
v_rut administrador.rut_administrador%type,
v_nombre administrador.nombre%type,
v_contrasena administrador.contrasena%type,
v_apepa administrador.apellido_paterno%type,
v_apema administrador.apellido_materno%type,
v_dire administrador.direccion%type,
v_tele administrador.telefono%type,
v_email administrador.email%type,
v_id_comuna administrador.id_comuna%type,
v_fechana administrador.fecha_nacimiento%type,
v_detall administrador.detalle%type,
v_foto administrador.foto%type)

is
BEGIN
UPDATE ADMINISTRADOR
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
WHERE rut_administrador = v_rut;

commit;
exception
WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al actualizar al administrador '||v_rut||'. Operacion interrumpida.');
rollback;
end;

/
