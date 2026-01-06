class Mopoken:
    def __init__(self, level):
        self.level = int(level)

    def name(self):
        return self.__class__.__name__

    def get_advantages(self):
        return []

    def get_disadvantages(self):
        return []

    def __str__(self):
        return f"{self.name}#{self.level}"
