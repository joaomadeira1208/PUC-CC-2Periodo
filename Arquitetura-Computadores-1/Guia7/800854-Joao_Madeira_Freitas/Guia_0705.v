// Guia_0705
// João Madeira Carneiro Braga de Freitas - 800854
module Guia_0705 (
    input a,
    input b,
    input [2:0] select,
    output out
);

wire not_a, not_b;
wire and_result, nand_result, xor_result, xnor_result, or_result, nor_result;

not NOT1 (not_a, a);
not NOT2 (not_b, b);
and AND1 (and_result, a, b);
nand NAND1 (nand_result, a, b);
xor XOR1 (xor_result, a, b);
xnor XNOR1 (xnor_result, a, b);
or OR1 (or_result, a, b);
nor NOR1 (nor_result, a, b);

assign out = (select == 3'b000) ? not_a :
             (select == 3'b001) ? not_b :
             (select == 3'b010) ? and_result :
             (select == 3'b011) ? nand_result :
             (select == 3'b100) ? xor_result :
             (select == 3'b101) ? xnor_result :
             (select == 3'b110) ? or_result :
             nor_result;

endmodule



module test_Guia_0705;

reg a;
reg b;
reg [2:0] select;
wire out;

Guia_0705 LU (
    .a(a),
    .b(b),
    .select(select),
    .out(out)
);



initial begin
    $display("Guia_0705 - Teste da Unidade Lógica");
    $display("a b select out");
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 0;
    b = 0;
    select = 3'b000; // Selecionar NOT(a)
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 0;
    b = 0;
    select = 3'b001; // Selecionar NOT(b)
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 1;
    b = 0;
    select = 3'b010; // Selecionar AND(a, b)
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 1;
    b = 1;
    select = 3'b011; // Selecionar NAND(a, b)
    #1 $monitor("%b %b %b     %b", a, b, select, out);

    a = 0;
    b = 0;
    select = 3'b100; // Selecionar XOR(a, b)
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 0;
    b = 1;
    select = 3'b101; // Selecionar XNOR(a, b)
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 1;
    b = 0;
    select = 3'b110; // Selecionar OR(a, b)
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    a = 1;
    b = 1;
    select = 3'b111; // Selecionar NOR(a, b)
    #1 $monitor("%b %b %b     %b", a, b, select, out);
    
    $finish;
end

endmodule
