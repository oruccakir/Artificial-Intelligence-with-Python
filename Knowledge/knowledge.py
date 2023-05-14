"""
## Knowledge - based agents 
-- agents that reason by operating on internai
-- representation of knowledge

## sentence : an assertion about the world in a knowledge representation language

## proposional logic , logical connectives : not , and, or, implication, biconditional

## model : assignment of a truth value to every propositional symbol (a "possible world")

## knowledge base : a set of sentences known by a knowledge - based agent

## entailment : in every model in which sentence a is true then, sentence b is also true (a entails b)

## inference : the process of deriving new sentences from old ones

########################## INFERENCE ALGORITHMS ########################################

--> MODEL CHECKING : 

-> To determine if (knowledge base) (KB) entails a :

* enumerate all possible models
* If in every model where KB is true, a is true, the KB entails a
* otherwise KB does not entail a

--> INFERENCE RULES: 

* modus ponens
* and elimination
* double negation elimination
* implication elimination
* biconditional elimination
* de morgan's law
* distributive property


Recap on search problems

1- initial state
2- actions
3- transition model
4- goal test
5- path cost function

In konwledge theorem proving : 

1 - initial state : starting knowledge base
2- actions : inference rules
3- transitional model : new knowledge base after inference
4- goal test : check statement we are trying to prove 
5- path cost function : number of steps in proof

## clause a disjuntion of literals

## conjunctive normal form : logical sentence that is a conjunction of clauses

----> Conversion to conjuntive normal form CNF

* eliminate biconditionals
* eliminate implications
* move - inwards using de morgan's laws
* use distributive law to distribute v wherever possible

--> Inference by resolution :

* to determibne if KB entais a :
* check if KB and -a is a controdiction (proof by contradiction)
* if contradiction the KB entails otherwise no entailment

* convert KB and -a to conjuntive normal form CNF
* keep checking to see if we can use resolution to produce a new clause
* if ever we produce the empty clause (equivalent to false) we have contradiction and KB entails a
* otherwise if we can not add new clauses no entailment

* first-order logic and universal quantification and existential quantification are used in proofs


"""