// Guia_0702
// João Madeira Carneiro Braga de Freitas - 800854
module Guia_0702 (
    input a,
    input b,
    input select,
    output out
);

wire or_result;
wire nor_result;

or OR1 (or_result, a, b);
nor NOR1 (nor_result, a, b);

assign out = (select) ? nor_result : or_result;

endmodule

module test_Guia_0702;

reg a;
reg b;
reg select;
wire out;

Guia_0702 LU (
    .a(a),
    .b(b),
    .select(select),
    .out(out)
);

initial begin
    $display("Guia_0702 - Teste da Unidade Lógica");
    $display("a b select out");
    $monitor("%b %b %b      %b", a, b, select, out);
    a = 0;
    b = 0;
    select = 0;
    #1 $monitor("%b %b %b      %b", a, b, select, out);
    
    a = 0;
    b = 1;
    select = 0;
    #1 $monitor("%b %b %b      %b", a, b, select, out);
    
    a = 1;
    b = 0;
    select = 0;
    #1 $monitor("%b %b %b      %b", a, b, select, out);
    
    a = 1;
    b = 1;
    select = 0;
    #1 $monitor("%b %b %b      %b", a, b, select, out);
    
    a = 0;
    b = 0;
    select = 1;
    #1 $monitor("%b %b %b      %b", a, b, select, out);
    
    a = 0;
    b = 1;
    select = 1;
    #1 $monitor("%b %b %b      %b", a, b, select, out);
    
    a = 1;
    b = 0;
    select = 1;
    #1 $monitor("%b %b %b      %b", a, b, select, out);
    
    a = 1;
    b = 1;
    select = 1;
    #1 $monitor("%b %b %b      %b", a, b, select, out);
    
    $finish;
end

endmodule
