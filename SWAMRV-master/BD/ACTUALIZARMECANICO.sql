--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure ACTUALIZARMECANICO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."ACTUALIZARMECANICO" (
v_rut mecanico.rut_mecanico%type,
v_nombre mecanico.nombre%type,
v_apepa mecanico.apellido_paterno%type,
v_apema mecanico.apellido_materno%type,
v_dire mecanico.direccion%type,
v_tele mecanico.telefono%type,
v_email mecanico.email%type,
v_id_comuna mecanico.id_comuna%type,
v_fechana mecanico.fecha_nacimiento%type,
v_detall mecanico.detalle%type,
v_foto mecanico.foto%type)

is
BEGIN
UPDATE MECANICO
SET nombre = v_nombre,
apellido_paterno = v_apepa,
apellido_materno = v_apema,
direccion = v_dire,
telefono = v_tele,
email = v_email,
id_comuna = v_id_comuna,
fecha_nacimiento = v_fechana,
detalle = v_detall,
foto = v_foto
WHERE rut_mecanico = v_rut;

commit;
exception
WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al actualizar al Mecanico '||v_rut||'. Operacion interrumpida.');
rollback;
end;

/
