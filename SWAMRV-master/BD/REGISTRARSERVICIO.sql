--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure REGISTRARSERVICIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."REGISTRARSERVICIO" (
v_idservicio servicio.id_servicio%type,
v_nombre servicio.nombre%type,
v_descripcion servicio.descripcion%type
)

is
BEGIN
INSERT INTO SERVICIO(id_servicio, nombre, descripcion) 
VALUES (v_idservicio, v_nombre, v_descripcion);

commit;
exception
WHEN DUP_VAL_ON_INDEX then
DBMS_OUTPUT.PUT_LINE ('Servicio Ya existe en la Base de Datos');

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al registrar el servicio '||v_nombre||'. Operacion interrumpida.');
rollback;
end;

/
