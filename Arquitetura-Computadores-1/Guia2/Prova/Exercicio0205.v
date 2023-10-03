function reals(reg[7:0], reg[7:0]);
    begin   

module Exercicio0205;
    reg[7:0] a = 8'b101;
    reg[7:0] b = 8'b11;
    reg[7:0] a_fractional = 8'b01;
    reg[7:0] b_fractional = 8'b111;
    real c = 0;
    real d = 0;

    initial 
        begin: main
            c = a + b;
            d = a_fractional + b_fractional;
            $display("(PARTE INTEIRA) %8b + %8b = %8b", a , b, c);


        end
endmodule