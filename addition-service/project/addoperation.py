""" Addoperation class """


class AddOperation:
    """ Class for add operation """

    def __init__(self, num1, num2):
        """ Init function """
        self.num1 = num1
        self.num2 = num2
        self.result = 0

    def process(self):
        """ Process function to perform operation"""
        self.result = self.num1 + self.num2

    def get_output_json(self):
        """ Function to create output json """
        self.process()
        output_json = {
            "input": {
                "num1": self.num1,
                "num2": self.num2,
            },
            "result":
            {
                "operation": "add",
                "value": self.result
            }
        }
        return output_json
