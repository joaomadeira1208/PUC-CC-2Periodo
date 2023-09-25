// Guia_0804
// João Madeira Carneiro Braga de Freitas - 800854

module Guia_0804 (output s, input [5:0] a, input [5:0] b);
    wire [5:0] xor_result;

    
    assign xor_result[0] = a[0] ^ b[0];
    assign xor_result[1] = a[1] ^ b[1];
    assign xor_result[2] = a[2] ^ b[2];
    assign xor_result[3] = a[3] ^ b[3];
    assign xor_result[4] = a[4] ^ b[4];
    assign xor_result[5] = a[5] ^ b[5];

    
    assign s = (xor_result[0] | xor_result[1] | xor_result[2] | xor_result[3] | xor_result[4] | xor_result[5]);

endmodule // Guia_0804

module test_Guia_0804;
    // Define os sinais de entrada e saída do teste
    reg [5:0] A, B;
    wire S;

    Guia_0804 LU (S, A, B);

    // Define os valores de teste
    initial begin
        // Teste 1: A e B são iguais
        A = 6'b110110;
        B = 6'b110110;
        #10; 
        $display("Teste 1: A = %b, B = %b, S = %b (Esperado: 0)", A, B, S);

        // Teste 2: A e B são diferentes
        A = 6'b110110;
        B = 6'b101010;
        #10;
        $display("Teste 2: A = %b, B = %b, S = %b (Esperado: 1)", A, B, S);

        

        $finish; // Termina a simulação
    end
endmodule // test_Guia_0804
