// Guia_0805
// João Madeira Carneiro Braga de Freitas - 800854

module halfAdder (output s, output c, input a, input b);
    xor XOR1 (s, a, b);
    and AND1 (c, a, b);
endmodule // halfAdder

module fullAdder (output s, output c, input a, input b, input cin);
    wire s1, s2, c1, c2, c3;

    halfAdder HA1 (s1, c1, a, b);
    halfAdder HA2 (s2, c2, s1, cin);
    or OR1 (c3, c1, c2);

    assign s = s2;
    assign c = c3;
endmodule // fullAdder

module Guia_0801 (output [6:0] sum, input [5:0] a, input [5:0] b);
    wire [5:0] c;
    wire s0, s1, s2, s3, s4, s5;

    fullAdder FA0 (s0, c[0], a[0], b[0], 1'b0);
    fullAdder FA1 (s1, c[1], a[1], b[1], c[0]);
    fullAdder FA2 (s2, c[2], a[2], b[2], c[1]);
    fullAdder FA3 (s3, c[3], a[3], b[3], c[2]);
    fullAdder FA4 (s4, c[4], a[4], b[4], c[3]);
    fullAdder FA5 (s5, c[5], a[5], b[5], c[4]);

    assign sum = {s5, s4, s3, s2, s1, s0, c[5]};
endmodule // Guia_0801

module Guia_0805 (output [5:0] complemento_de_2, input [5:0] valor_binario);
    wire [5:0] complemento_de_1;
    wire carryOut = 1'b1;

    
    assign {complemento_de_1[0], carryOut} = ~valor_binario[0] + carryOut;

    assign {complemento_de_1[1], carryOut} = ~valor_binario[1] + carryOut;

    assign {complemento_de_1[2], carryOut} = ~valor_binario[2] + carryOut;

    assign {complemento_de_1[3], carryOut} = ~valor_binario[3] + carryOut;

    assign {complemento_de_1[4], carryOut} = ~valor_binario[4] + carryOut;

    assign {complemento_de_1[5], carryOut} = ~valor_binario[5] + carryOut;

    
    wire [5:0] um = 6'b000001;
    wire carryIn = 1'b0;
    wire [5:0] soma;

    fullAdder FAs[4:0] (
        .s(soma[4:0]),
        .c({carryOut, carryOut, carryOut, carryOut, carryOut}),
        .a(complemento_de_1[4:0]),
        .b(um[4:0]),
        .cin(carryIn)
    );

    assign soma[5] = carryOut; 

    assign complemento_de_2 = soma;
endmodule // Guia_0805


module test_Guia_0805;
    // Define os sinais de entrada e saída do teste
    reg [5:0] Valor_Binario;
    wire [5:0] Complemento_de_2;

 
    Guia_0805 LU (.complemento_de_2(Complemento_de_2), .valor_binario(Valor_Binario));

    // Define os valores de teste
    initial begin
        // Teste 1: Valor Binário = 6'b110110 (54 em decimal)
        Valor_Binario = 6'b110110;
        #100; 
        $display("Teste 1: Valor Binário = %b, Complemento de 2 = %b (Esperado: 6'b101010)", Valor_Binario, Complemento_de_2);

        // Teste 2: Valor Binário = 6'b000001 (1 em decimal)
        Valor_Binario = 6'b000001;
        #100; 
        $display("Teste 2: Valor Binário = %b, Complemento de 2 = %b (Esperado: 6'b111111)", Valor_Binario, Complemento_de_2);

        // Adicione mais testes conforme necessário

        $finish; // Termina a simulação
    end
endmodule // test_Guia_0805
