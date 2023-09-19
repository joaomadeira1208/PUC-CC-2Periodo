// Guia_0704
// João Madeira Carneiro Braga de Freitas - 800854
module Guia_0704 (
    input a,
    input b,
    input [1:0] select,
    output out
);

wire xor_result, xnor_result, or_result, nor_result;

xor XOR1 (xor_result, a, b);
xnor XNOR1 (xnor_result, a, b);
or OR1 (or_result, a, b);
nor NOR1 (nor_result, a, b);

assign out = (select == 2'b00) ? xor_result :
             (select == 2'b01) ? xnor_result :
             (select == 2'b10) ? or_result :
             nor_result;

endmodule

module test_Guia_0704;

reg a;
reg b;
reg [1:0] select;
wire out;

Guia_0704 LU (
    .a(a),
    .b(b),
    .select(select),
    .out(out)
);

initial begin
    $display("Guia_0704 - Teste da Unidade Lógica");
    $display("a b select out");
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 0;
    b = 0;
    select = 2'b00; // Selecionar XOR
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 0;
    b = 1;
    select = 2'b00; // Selecionar XOR
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 1;
    b = 0;
    select = 2'b00; // Selecionar XOR
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 1;
    b = 1;
    select = 2'b00; // Selecionar XOR
    #1 $monitor("%b %b %b     %b", a, b, select, out);

    a = 0;
    b = 0;
    select = 2'b01; // Selecionar XNOR
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 0;
    b = 1;
    select = 2'b01; // Selecionar XNOR
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 1;
    b = 0;
    select = 2'b01; // Selecionar XNOR
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 1;
    b = 1;
    select = 2'b01; // Selecionar XNOR
    #1 $monitor("%b %b %b     %b", a, b, select, out);

    a = 0;
    b = 0;
    select = 2'b10; // Selecionar OR
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 0;
    b = 1;
    select = 2'b10; // Selecionar OR
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 1;
    b = 0;
    select = 2'b10; // Selecionar OR
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 1;
    b = 1;
    select = 2'b10; // Selecionar OR
    #1 $monitor("%b %b %b     %b", a, b, select, out);

    a = 0;
    b = 0;
    select = 2'b11; // Selecionar NOR
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 0;
    b = 1;
    select = 2'b11; // Selecionar NOR
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 1;
    b = 0;
    select = 2'b11; // Selecionar NOR
    #1 $monitor("%b %b %b     %b", a, b, select, out);

    a = 1;
    b = 1;
    select = 2'b11; // Selecionar NOR
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    $finish;
end

endmodule
