--------------------------------------------------------
--  File created - miércoles-junio-20-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure OBTENERCOMUNA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."OBTENERCOMUNA" (
v_nombre IN comuna.nombre%type,
v_id_comuna OUT comuna.id_comuna%type)
as
BEGIN
SELECT id_comuna
INTO v_id_comuna
FROM COMUNA WHERE NOMBRE = v_nombre;
exception
WHEN NO_DATA_FOUND then
dbms_output.put_line('Comuna '||v_nombre||' no existe');
WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al buscar la Comuna '||v_nombre||'. Operacion interrumpida.');
commit;
end;

/
