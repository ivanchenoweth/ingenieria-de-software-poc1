--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure REGISTRARFALLA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."REGISTRARFALLA" (
v_id_falla falla.id_falla%type,
v_nombre falla.nombre%type,
v_descripcion falla.descripcion%type,
v_foto falla.foto%type)

is
BEGIN
INSERT INTO FALLA(id_falla, nombre, descripcion, foto) 
VALUES (v_id_falla, v_nombre, v_descripcion, v_foto);

commit;
exception
WHEN DUP_VAL_ON_INDEX then
DBMS_OUTPUT.PUT_LINE ('Falla Ya existe en la Base de Datos');

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al registrar la falla '||v_nombre||'. Operacion interrumpida.');
rollback;
end;

/
