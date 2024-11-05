# CO PROJECT-PHASE-3

#### Implemented pipeline 
* we have used a while loop in pipeline method to implement the pipelining which consists five functions corrsponding five stages of a pipeline(IF,ID,EX,MEM,WB).
* we have used temperory registers and boolean variables between stages of pipeline for the flow of pipeline
* In IF program counter will be known and data hazards will be detected
* In ID registers are fetched and branch predictions are taken
* In EX corresponding executions are done based on the opcode and stored in a temperory register and propogated to next stage of pipeline through another temperory register
* In MEM operations related to lw,sw,la are executed
#### Implemented data hazards
* Read After Write hazard is taken care using stalls

#### Implemented branch predictor
* initialised a boolean variable to "not taken" and checked for original branch excution at EX stage

#### How to run?
* download the zip file 
* check the paths of files in Main.java
* Debug to get the output

#### Output
* updated registers of two cores
* Memory after executing programs in two cores
* Number of cycles
* Number of stalls

## Minutes of meeting
## Mar 5
* Thought about the basic outline for implementing pipeline
## Mar 6
* Implemented pipelining 
* Thought about data hazards and incorporating stalls 
## Mar 7
* Installed stalls to existing code and taken care of data hazards for the non-branch instructions.
* debugged the errors
## Mar 8
* Implemented jump instructions and memory related instructions and debugged the existing code
## Mar 9
* Implemented branch instructions to follow the pipeline
## Mar 10
* Debugged the errors in the existing code
* Tried data forwarding but only pipeline and stalls are working but output is not updating.
