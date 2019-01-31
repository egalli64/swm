-- PL/SQL CURSOR
--
set serveroutput on


-- exact fetch for a single row with SELECT INTO 
declare
    v_first_name coders.first_name%type;
    v_last_name coders.last_name%type;
begin
    select first_name, last_name
    into v_first_name, v_last_name
    from coders
    WHERE coder_id = 103;
    
    dbms_output.put_line('[' || v_first_name || ' ' || v_last_name || ']');
end;
/

-- explicit FETCH CURSOR
declare
    v_coder_id coders.coder_id%type;
    v_last_name coders.last_name%type;
    v_hire_date coders.hire_date%type;
    cursor v_coder_cursor is
        select coder_id, last_name, hire_date
        from coders
        order by last_name;
begin
    open v_coder_cursor;

    loop
        fetch v_coder_cursor
        into v_coder_id, v_last_name, v_hire_date;
        exit when v_coder_cursor%notfound;

        dbms_output.put_line('[' || v_coder_id || ', ' || v_last_name || ', ' || v_hire_date || ']');
    end loop;
    close v_coder_cursor;
end;
/

-- implicit FETCH CURSOR in a FOR LOOP
declare
    cursor v_coder_cursor is
        select coder_id, last_name, hire_date
        from coders
        order by last_name;
begin
    for v_cur in v_coder_cursor loop
        dbms_output.put_line('[' || v_cur.coder_id || ', ' || v_cur.last_name || ', ' || v_cur.hire_date || ']');
    end loop;
end;
/

-- OPEN CURSOR FOR
declare
    type t_coders_cursor is ref cursor return coders%rowtype;
    v_coder_cursor t_coders_cursor;
    v_cur coders%rowtype;
begin
    open v_coder_cursor for select * from coders where coder_id between 104 and 106;
    loop
        fetch v_coder_cursor into v_cur;
        exit when v_coder_cursor%notfound;
        dbms_output.put_line('[' || v_cur.coder_id || ', ' || v_cur.last_name || ', ' || v_cur.hire_date || ']');
    end loop;

    close v_coder_cursor;
end;
/

-- Unconstrained CURSOR
DECLARE
    TYPE t_cursor IS REF CURSOR;
    v_cursor t_cursor;
    v_cod coders%ROWTYPE;
BEGIN
    OPEN v_cursor FOR SELECT * from coders where coder_id between 104 and 106;
    LOOP
        FETCH v_cursor INTO v_cod;
        EXIT WHEN v_cursor%NOTFOUND;
        dbms_output.put_line('[' || v_cod.coder_id || ', ' || v_cod.last_name || ', ' || v_cod.hire_date || ']');
    END LOOP;    
    
    CLOSE v_cursor;
END;
/
