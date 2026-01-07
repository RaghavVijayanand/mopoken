from parse_mopokens import ParseMopokens
from get_battle_result import getResult

def main():

    lines = []  
    while len(lines) < 2:
        line = input().strip()
        if line:
           lines.append(line)
    if len(lines) < 2:
        return
    my_team = ParseMopokens.parse_mopokens(lines[0])
    opp_team = ParseMopokens.parse_mopokens(lines[1])
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
                            if getResult.get_battle_results(candidate[idx], opp_team[idx]) == 1:
                                wins += 1 
                        if wins >= 3:
                            print(";".join([str(p) for p in candidate]))
                            return
    print("no chance of winning")

if __name__ == "__main__":
    main()
