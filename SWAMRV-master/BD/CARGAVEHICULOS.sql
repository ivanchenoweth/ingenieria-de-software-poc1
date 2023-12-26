--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure CARGAVEHICULOS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."CARGAVEHICULOS" (
v_patente IN vehiculo.patente%type,
v_chasis OUT vehiculo.chasis%type,
v_ano OUT vehiculo.ano%type,
v_color OUT vehiculo.color%type,
v_marca OUT vehiculo.marca%type,
v_modelo OUT vehiculo.modelo%type,
v_fechain OUT vehiculo.fecha_ingreso%type,
v_foto OUT vehiculo.foto%type)

as
BEGIN
SELECT chasis, ano, color, marca, modelo, fecha_ingreso, foto
INTO v_chasis, v_ano, v_color, v_marca, v_modelo, v_fechain, v_foto
FROM VEHICULO WHERE PATENTE = v_patente;
commit;
exception
WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('PATENTE '||v_patente||' no existe en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al cargar al Conductor '||v_patente||'. Operacion interrumpida.');
rollback;
end;

/
