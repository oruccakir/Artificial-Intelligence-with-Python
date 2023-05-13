"""
Tic Tac Toe Player
"""

import math
import copy

X = "X"
O = "O"
EMPTY = None


def initial_state():
    """
    Returns starting state of the board.
    """
    return [[EMPTY, EMPTY, EMPTY],
            [EMPTY, EMPTY, EMPTY],
            [EMPTY, EMPTY, EMPTY]]


def player(board):
    """
    Returns player who has the next turn on a board.
    """
    numberOfX = 0
    numberOfO = 0
    numberOFEmpty = 0

    for row in board:
        for element in row:
            if(element == X):
                numberOfX=numberOfX+1
            elif(element == O):
                numberOfO=numberOfO+1
            else:
                numberOFEmpty=numberOFEmpty+1
    
    if(numberOFEmpty == 9 or numberOfX < numberOfO):
        return X
    else:
        return O


    raise NotImplementedError


def actions(board):
    """
    Returns set of all possible actions (i, j) available on the board.
    """
    rows = len(board)

    columns = len(board[0])

    possible_actions_set = set()

    for i in range(rows):
        for j in range(columns):
            if(board[i][j] == EMPTY):
                t = (i,j)
                possible_actions_set.add(t)
    
    return possible_actions_set


    raise NotImplementedError


def result(board, action):
    """
    Returns the board that results from making move (i, j) on the board.
    """

    ## if action is not a valid move then raise an exception

    ## out of board control

    if(action[0] < 0 or action[0] > 2) :
        raise Exception("invalid move")
    if(action[1] < 0 or action[1] > 2):
        raise Exception("invalid move")
    
    ## the control of movement to already filled place

    i = action[0]
    j = action[1]

    if(board[i][j] != EMPTY):
        raise Exception("invalid move")
    
    ## firstly, deepcopy should be made not to lose infromation 

    copy_matrix = copy.deepcopy(board)

    whose_turn = player(board)

    if(whose_turn == X):
        copy_matrix[i][j] = X
    else:
        copy_matrix[i][j] = O

    ## and return the changed copy matrix without changing the original board

    return copy_matrix


    raise NotImplementedError


def winner(board):
    """
    Returns the winner of the game, if there is one.
    """
    rows = len(board)

    columns = len(board[0])

    ## makes control of horizontally winning

    for i in range(rows):
        numberX = 0
        numberO = 0
        for j in range(columns):
            if(board[i][j] == X):
                numberX = numberX+1
            elif(board[i][j] == O):
                numberO = numberO+1
        if(numberX == 3):
            return X
        elif(numberO == 3):
            return O
        
    ## makes control of vertically winning
    
    for j in range(columns):
        numberX = 0
        numberO = 0
        for i in range(rows):
            if(board[i][j] == X):
                numberX = numberX+1
            elif(board[i][j] == O):
                numberO = numberO+1
        if(numberX == 3):
            return X
        elif(numberO == 3):
            return O
        
    ## makes control of diagonally winning

    if(board[0][0] == X and board[1][1] == X and board[2][2] == X  ):
        return X
    
    if(board[0][2] == X and board[1][1] == X and board[2][0] == X  ):
        return X
    
    if(board[0][0] == O and board[1][1] == O and board[2][2] == O  ):
        return O
    
    if(board[0][2] == O and board[1][1] == O and board[2][0] == O  ):
        return O
    
    ## if the game has no winner return none

    return None


    raise NotImplementedError


def terminal(board):
    """
    Returns True if game is over, False otherwise.
    """
    ## if someone wins the game, game is over

    temp_result = winner(board)

    if(temp_result == X or temp_result == O):
        return True
    
    ## if none wins game and board is full, game is over 
    
    numberof_empty_cells = 0

    for row in board:
        for element in row:
            if(element == EMPTY):
                numberof_empty_cells = numberof_empty_cells+1
    
    if(numberof_empty_cells == 0):
        return True
    
    ## otherwise game is not finished return false

    return False


    raise NotImplementedError


def utility(board):
    """
    Returns 1 if X has won the game, -1 if O has won, 0 otherwise.
    """
    temp_result = winner(board)

    ## Note that this function will be called if(terminal(board)) is True
    ## so, at most three condition will occur

    if(temp_result == X):
        return 1
    elif(temp_result == O):
        return -1
    else:
        return 0


    raise NotImplementedError


def minimax(board):
    """
    Returns the optimal action for the current player on the board.
    """
    raise NotImplementedError
