# Rock-paper-scissors-lizard-Spock


# The key idea of this program is to equate the strings
# "rock", "paper", "scissors", "lizard", "Spock" to numbers
# as follows:
#
# 0 - rock
# 1 - Spock
# 2 - paper
# 3 - lizard
# 4 - scissors

import random

# helper functions

def name_to_number(name):
    '''
    Function to convert names to numbers.
    Returns the number corresponding to the name.
    '''

    #Case sensitive
    if name == "rock":
        return 0
    elif name == "Spock":
        return 1
    elif name == "paper":
        return 2
    elif name == "lizard":
        return 3
    elif name == "scissors":
        return 4
    else:
        print "Invalid name - " + name


def number_to_name(number):
    '''
    Function to convert numbers to names.
    Returns the name corresponding to the number.
    '''

    if number == 0:
        return "rock"
    elif number == 1:
        return "Spock"
    elif number == 2:
        return "paper"
    elif number == 3:
        return "lizard"
    elif number == 4:
        return "scissors"
    else:
        print "Invalid number - " + number


def rpsls(player_choice):
    '''
    Function to randomly choose a comp_number and play
    a game of rock-paper-scissors-lizard-spock, given
    the player's choice.
    '''

    # print a blank line to separate consecutive games
    print "\n"

    #player's choice
    print "Player chooses " + player_choice

    # convert the player's choice to player_number
    player_number = name_to_number(player_choice)

    # compute random guess for comp_number
    comp_number = random.randrange(0,5)

    # convert comp_number to comp_choice
    comp_choice = number_to_name(comp_number)

    # computer's choice
    print "Computer chooses " + comp_choice

    # determining the winner
    result = (comp_number - player_number) % 5

    if result == 1 or result == 2:
        print "Computer wins!"
    elif result == 3 or result == 4:
        print "Player wins!"
    else:
        print "Player and computer tie!"


# Tests
rpsls("rock")
rpsls("Spock")
rpsls("paper")
rpsls("lizard")
rpsls("scissors")

