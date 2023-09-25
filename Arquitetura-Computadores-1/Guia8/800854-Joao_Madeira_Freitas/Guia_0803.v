// Guia_0803
// João Madeira Carneiro Braga de Freitas - 800854

module Guia_0803 (output s, input [5:0] a, input [5:0] b);
    wire [5:0] xnor_result;

    assign xnor_result[0] = ~(a[0] ^ b[0]);
    assign xnor_result[1] = ~(a[1] ^ b[1]);
    assign xnor_result[2] = ~(a[2] ^ b[2]);
    assign xnor_result[3] = ~(a[3] ^ b[3]);
    assign xnor_result[4] = ~(a[4] ^ b[4]);
    assign xnor_result[5] = ~(a[5] ^ b[5]);

    
    assign s = (xnor_result[0] & xnor_result[1] & xnor_result[2] & xnor_result[3] & xnor_result[4] & xnor_result[5]);

endmodule // Guia_0803

module test_Guia_0803;
    // Define os sinais de entrada e saída do teste
    reg [5:0] A, B;
    wire S;

    Guia_0803 LU (S, A, B);

    // Define os valores de teste
    initial begin
        // Teste 1: A e B são iguais
        A = 6'b110110;
        B = 6'b110110;
        #10; 
        $display("Teste 1: A = %b, B = %b, S = %b (Esperado: 1)", A, B, S);

        // Teste 2: A e B são diferentes
        A = 6'b110110;
        B = 6'b101010;
        #10;
        $display("Teste 2: A = %b, B = %b, S = %b (Esperado: 0)", A, B, S);

        // Adicione mais testes conforme necessário

        $finish; // Termina a simulação
    end
endmodule // test_Guia_0803
