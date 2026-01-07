import sys
from mopokens_types.fire import Fire
from mopokens_types.water import Water
from mopokens_types.grass import Grass
from mopokens_types.electric import Electric
from mopokens_types.psychic import Psychic
from mopokens_types.ghost import Ghost
from mopokens_types.fighting import Fighting

class ParseMopokens():
    def parse_mopokens(input_str):
        type_dict = {
            "fire": Fire,
            "water": Water,
            "grass": Grass,
            "electric": Electric,
            "psychic": Psychic,
            "ghost": Ghost,
            "fighting": Fighting
        }
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