class getResult():
    def get_battle_results(my_poke, opp_poke):
        my_adv = my_poke.get_advantages()
        opp_adv = opp_poke.get_advantages()
        my_has_adv = opp_poke.name in my_adv
        opp_has_adv = my_poke.name in opp_adv
        if my_has_adv and opp_poke.level >= 2 * my_poke.level:
            my_has_adv = False
        if opp_has_adv and my_poke.level >= 2 * opp_poke.level:
            opp_has_adv = False
        if my_has_adv != opp_has_adv:
            return 1 if my_has_adv else -1
        return (my_poke.level > opp_poke.level) - (my_poke.level < opp_poke.level)