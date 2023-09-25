// Guia_0801
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

module test_Guia_0801;
    reg [5:0] A, B;
    wire [6:0] S;

    
    Guia_0801 AU (S, A, B);

    
    initial begin
        $monitor("Teste: A = %6d, B = %6d, S = %6d", A, B, S);

        // Test 1: A = 6, B = 9
        A = 6;
        B = 9;
        #10;

        // Test 2: A = 15, B = 15
        A = 15;
        B = 15;
        #10;

        

        $finish; 
    end
endmodule // test_Guia_0801
