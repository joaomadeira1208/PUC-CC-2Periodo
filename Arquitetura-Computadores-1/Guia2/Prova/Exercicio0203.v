module Exercicio0203;
    reg[7:0] b = 8'b010010_00;

    initial 
        begin: main
            $display("Exercise 3 - Guide 2");
            $display("0.%8b (2)", b);
            $display("0.%o%o%o%o (4)", b[7:6], b[5:4], b[3:2], b[1:0]);
            $display("0.%o%o%o (8)", b[7:6], b[5:3], b[2:0]);
            $display("0.%h%h (16)", b[7:4], b[3:0]);
        end
endmodule