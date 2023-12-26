--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure REGISTRARMECANICO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."REGISTRARMECANICO" (
v_rut mecanico.rut_mecanico%type,
v_nombre mecanico.nombre%type,
v_apepa mecanico.apellido_paterno%type,
v_apema mecanico.apellido_materno%type,
v_dire mecanico.direccion%type,
v_tele mecanico.telefono%type,
v_email mecanico.email%type,
v_id_comuna mecanico.id_comuna%type,
v_fechain mecanico.fecha_ingreso%type,
v_fechana mecanico.fecha_nacimiento%type,
v_detall mecanico.detalle%type,
v_foto mecanico.foto%type)

is
BEGIN
INSERT INTO MECANICO(rut_mecanico, nombre, apellido_paterno, apellido_materno, direccion, telefono, email, id_comuna, fecha_ingreso, fecha_nacimiento, detalle, foto) 
VALUES (v_rut, v_nombre, v_apepa, v_apema, v_dire, v_tele, v_email, v_id_comuna, v_fechain, v_fechana, v_detall, v_foto);

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
