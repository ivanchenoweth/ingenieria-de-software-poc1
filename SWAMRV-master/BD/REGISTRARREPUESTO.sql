--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure REGISTRARREPUESTO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."REGISTRARREPUESTO" (
v_id_repuesto repuesto.id_repuesto%type,
v_nombre repuesto.nombre%type,
v_descripcion repuesto.descripcion%type,
v_foto repuesto.foto%type)

is
BEGIN
INSERT INTO REPUESTO(id_repuesto, nombre, descripcion, foto) 
VALUES (v_id_repuesto, v_nombre, v_descripcion, v_foto);

commit;
exception
WHEN DUP_VAL_ON_INDEX then
DBMS_OUTPUT.PUT_LINE ('Repuesto Ya existe en la Base de Datos');

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al registrar el Repuesto '||v_nombre||'. Operacion interrumpida.');
rollback;
end;

/
