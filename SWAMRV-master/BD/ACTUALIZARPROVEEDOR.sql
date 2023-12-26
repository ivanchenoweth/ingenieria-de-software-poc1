--------------------------------------------------------
--  File created - martes-junio-19-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure ACTUALIZARPROVEEDOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SOPRAFAMRV"."ACTUALIZARPROVEEDOR" (
v_nombre proveedor.nombre%type,
v_idproveedor proveedor.id_proveedor%type,
v_telefono proveedor.telefono%type,
v_descripcion proveedor.descripcion%type,
v_direccion proveedor.direccion%type,
v_email proveedor.email%type,
v_idcomuna proveedor.id_comuna%type)

is
BEGIN
UPDATE PROVEEDOR
set nombre = v_nombre,
telefono = v_telefono,
descripcion = v_descripcion,
direccion = v_direccion,
email = v_email,
id_comuna = v_idcomuna
WHERE id_proveedor = v_idproveedor;

commit;
exception

WHEN NO_DATA_FOUND then
DBMS_OUTPUT.PUT_LINE ('No se han encontrado los datos en la Base de Datos');

WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20200, 'Ha ocurrido un error al actualizar el proveedor '||v_nombre||'. Operacion interrumpida.');
rollback;
end;

/
