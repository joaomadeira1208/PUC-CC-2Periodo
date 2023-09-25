// Guia_0802
// João Madeira Carneiro Braga de Freitas - 800854

module halfSubtractor(output d, output b, input x, input y);
   
    xor XOR1 (d, x, y);
    
    and AND1 (b, ~x, d);
endmodule // halfSubtractor

module fullSubtractor(output d, output b, input x, input y, input bin);
    wire d1, d2, b1, b2;

    
    halfSubtractor HS1 (d1, b1, x, y);
    
    halfSubtractor HS2 (d2, b2, d1, bin);
    
    or OR1 (b, b1, b2);
    assign d = d2;
endmodule // fullSubtractor

module Guia_0802 (output [5:0] diff, input [5:0] a, input [5:0] b);
    wire borrow[5:0];
    wire d0, d1, d2, d3, d4, d5;

    // Subtrator completo de 6 bits
    fullSubtractor FS0 (d0, borrow[0], a[0], b[0], 1'b0);
    fullSubtractor FS1 (d1, borrow[1], a[1], b[1], borrow[0]);
    fullSubtractor FS2 (d2, borrow[2], a[2], b[2], borrow[1]);
    fullSubtractor FS3 (d3, borrow[3], a[3], b[3], borrow[2]);
    fullSubtractor FS4 (d4, borrow[4], a[4], b[4], borrow[3]);
    fullSubtractor FS5 (d5, borrow[5], a[5], b[5], borrow[4]);

    assign diff = {d5, d4, d3, d2, d1, d0};
endmodule // Guia_0802


module test_Guia_0802;
    reg [5:0] A, B;
    wire [5:0] D;

    
    Guia_0802 AU (D, A, B);

    // Define os valores de teste
    initial begin
        // Teste 1: A = 6, B = 9
        A = 10;
        B = 5;
      
        #10;
        $display("Teste 1: A = %2d, B = %2d, D = %2d", A, B, D);

        // Teste 2: A = 15, B = 15
        A = 15;
        B = 15;
        
        #10;
        $display("Teste 2: A = %2d, B = %2d, D = %2d", A, B, D);

        

        $finish; // Termina a simulação
    end
endmodule // test_Guia_0802
