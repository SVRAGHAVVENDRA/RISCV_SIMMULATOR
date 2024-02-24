package Test;

import java.util.HashMap;

public class Core {
        int registers[];
        int pc;
        String program[];
        int store;
        Core() {
            this.registers = new int[32];
            this.pc=0;
            this.store=0;
            this.program=new String[0]; 
        }
        public int readMemory(String data, HashMap<String, Integer> map) {
            return  map.get(data);
            
        }
        public void execute(int memory[],HashMap<String, Integer> map) {
        while(pc!=program.length){
            if (pc >= program.length){
                return;
            }
            String parts[] = program[pc].trim().split("[,\\s]+");
            String opcode = parts[0].toLowerCase();

            if (opcode.equals("add")) {
                int rd = Integer.parseInt(parts[1].substring(1));
                int rs1 = Integer.parseInt(parts[2].substring(1));
                int rs2 = Integer.parseInt(parts[3].substring(1));
                registers[rd] = registers[rs1] + registers[rs2];
                pc++;
            }
            else if (opcode.equals("sub")) {
                int rd = Integer.parseInt(parts[1].substring(1));
                int rs1 = Integer.parseInt(parts[2].substring(1));
                int rs2 = Integer.parseInt(parts[3].substring(1));
                registers[rd] = registers[rs1] - registers[rs2];
                pc++;
            }
            else if (opcode.equals("ld")) {
                int rd = Integer.parseInt(parts[1].substring(1));
                int location = Integer.parseInt(parts[2]);
                registers[rd] = memory[location];
                pc++;
            }
            else if (opcode.equals("bne")) {
                int rs1 = Integer.parseInt(parts[1].substring(1));
                int rs2 = Integer.parseInt(parts[2].substring(1));
                String str = parts[3];
                if (registers[rs1] != registers[rs2]) {
                    int i;
                    for (i = 0; i < program.length; i++) {
                        if (i < program.length - 1 && program[i].startsWith(str)) {
                            pc = i + 1;
                            break;
                        }
                    }
                }
                else {
                    pc++;
                }
            }
            else if(opcode.equals("blt")){
                int rs1 = Integer.parseInt(parts[1].substring(1));
                int rs2 = Integer.parseInt(parts[2].substring(1));
                String str = parts[3];
                if (registers[rs1] < registers[rs2]) {
                    int i;
                    for (i = 0; i<program.length; i++) {
                        if (i < program.length - 1 && program[i].startsWith(str)) {
                            pc = i + 1;
                            break;
                        }
                    }
                }
                else {
                    pc++;
                }
            }
            else if(opcode.equals("bgt")){
                int rs1 = Integer.parseInt(parts[1].substring(1));
                int rs2 = Integer.parseInt(parts[2].substring(1));
                String str = parts[3];
                if (registers[rs1] >= registers[rs2]) {
                    int i;
                    for (i = 0; i<program.length; i++) {
                        if (i < program.length - 1 && program[i].startsWith(str)) {
                            pc = i + 1;
                            break;
                        }
                    }
                }
                else {
                    pc++;
                }
            }
            else if(opcode.equals("mv")){
                int rd = Integer.parseInt(parts[1].substring(1));
                int rs2 = Integer.parseInt(parts[2].substring(1));
                registers[rd] = registers[rs2];
                pc++;
            }
            else if (opcode.equals("jal")) {
                int rd = Integer.parseInt(parts[1].substring(1));
                String label = parts[2];
                
                for (int i = 0; i < program.length; i++) {
                    if (program[i].startsWith(label+":")) {
                        store = pc + 1; // Store the return address
                        registers[rd] = store;
                        pc = i+1;
                        break;
                    }
                   
                }
            } 
            else if (opcode.equals("ret")) {
                // Return from subroutine
                pc = store;
            }
            
            else if(opcode.equals("addi")){
                int rd=Integer.parseInt(parts[1].substring(1));
                int rs1 = Integer.parseInt(parts[2].substring(1));
                int r=Integer.parseInt(parts[3]);
                registers[rd] = registers[rs1] + r;
                pc++;
            }
            else if(opcode.equals("beq")){
                int rs1 = Integer.parseInt(parts[1].substring(1));
                int rs2 = Integer.parseInt(parts[2].substring(1));
                String str = parts[3];
                if (registers[rs1] == registers[rs2]) {
                    for (int i = 0; i < program.length; i++) {
                        if (program[i].startsWith(str+":")) {
                            pc = i;
                            break;
                        }
                    }
                }
                else {
                    pc++;
                }
            }
            else if (opcode.equals("lw")) {
                int rd = Integer.parseInt(parts[1].substring(1));
                if (parts[2].charAt(1)=='(') {
                    int offset = Integer.parseInt(parts[2].substring(0, parts[2].indexOf('(')));
                    int rs1 = Integer.parseInt(parts[2].substring(parts[2].indexOf('(') + 2, parts[2].indexOf(')')));
                    registers[rd] = memory[registers[rs1] + offset];
                }
                pc++;
            } 
            else if (opcode.equals("sw")) {
                int rd = Integer.parseInt(parts[1].substring(1));
                if (parts[2].charAt(1)=='(') {
                    int offset = Integer.parseInt(parts[2].substring(0, parts[2].indexOf('(')));
                    int rs1 = Integer.parseInt(parts[2].substring(parts[2].indexOf('(') + 2, parts[2].indexOf(')')));
                    memory[registers[rs1] + offset] = registers[rd];
                }
                pc++;
            }

            else if(opcode.equals("la")){
                int rd = Integer.parseInt(parts[1].substring(1));
                    int x = readMemory(parts[2], map);
                    registers[rd] = x;
                    pc++;
            }
            
            else if(opcode.equals("li")){
                int rd = Integer.parseInt(parts[1].substring(1));
                int r=Integer.parseInt(parts[2]);
                registers[rd] = r;
                pc++;
            }
            else if (opcode.equals("j")) {
                String label = parts[1];
                for (int i = 0; i < program.length; i++) {
                    if (program[i].startsWith(label+":")) {
                        pc = i+1;
                        break;
                    }
                }
            }
            else{
                pc++;
            }
        }
    }
}
