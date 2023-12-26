--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure REGISTRARVEHICULO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."REGISTRARVEHICULO" (
v_patente vehiculo.patente%type,
v_chasis vehiculo.chasis%type,
v_ano vehiculo.ano%type,
v_color vehiculo.color%type,
v_marca vehiculo.marca%type,
v_modelo vehiculo.modelo%type,
v_fechain vehiculo.fecha_ingreso%type,
v_foto vehiculo.foto%type)

is
BEGIN
INSERT INTO VEHICULO(patente, chasis, ano, color, marca, modelo, fecha_ingreso, foto) 
VALUES (v_patente, v_chasis, v_ano, v_color, v_marca, v_modelo, v_fechain, v_foto);

commit;
exception
WHEN DUP_VAL_ON_INDEX then
DBMS_OUTPUT.PUT_LINE ('VEHICULO Ya existe en la Base de Datos');

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al registrar al Vehiculo '||v_patente||'. Operacion interrumpida.');
rollback;
end;

/
