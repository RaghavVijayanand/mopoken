from fire import Fire
from water import Water
from grass import Grass
from electric import Electric
from psychic import Psychic
from ghost import Ghost
from fighting import Fighting
import sys

type_dict = {
    "fire": Fire,
    "water": Water,
    "grass": Grass,
    "electric": Electric,
    "psychic": Psychic,
    "ghost": Ghost,
    "fighting": Fighting
}

def parse_mopokens(input_str):
    mopokens = []
    seen = set()
    parts = input_str.strip().split(';')
    for part in parts:
        if not part:
            continue
        if '#' not in part:
            print("Invalid format")
            sys.exit(1)
        type_name, level = part.split('#', 1)
        if not level.isdigit():
            print("Invalid level")
            sys.exit(1)
        level = int(level)
        if type_name in seen:
            print("Duplicate type")
            sys.exit(1)
        seen.add(type_name)
        cls = type_dict.get(type_name.lower())
        if cls is None:
            print("Unknown type")
            sys.exit(1)
        mopokens.append(cls(level))
    if len(mopokens) != 5:
        print("must have 5 pokemons")
        sys.exit(1)
    return mopokens

def get_battle_result(my_poke, opp_poke):
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


def main():
    try:
        lines = []
        while len(lines) < 2:
            line = input().strip()
            if line:
               lines.append(line)
        if len(lines) < 2:
            return
        my_team = parse_mopokens(lines[0])
        opp_team = parse_mopokens(lines[1])
        for i in range(5):
            for j in range(5):
                if i == j: continue
                for k in range(5):
                    if k in [i, j]: continue
                    for l in range(5):
                        if l in [i, j, k]: continue
                        for m in range(5):
                            if m in [i, j, k, l]: continue
                            candidate= [my_team[i], my_team[j], my_team[k], my_team[l], my_team[m]]
                            wins = 0
                            for idx in range(5):
                                if get_battle_result(candidate[idx], opp_team[idx]) == 1:
                                    wins += 1 
                            if wins >= 3:
                                print(";".join([str(p) for p in candidate]))
                                return
        print("no chance of winning")
    except ValueError as e:
        print(f"{e}")
    except Exception as e:
        print(f"{e}")

if __name__ == "__main__":
    main()
