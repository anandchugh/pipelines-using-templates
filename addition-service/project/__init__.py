""" Creating instance of Flask app """

from flask import Flask, render_template, make_response, jsonify, request
from project.addoperation import AddOperation

app = Flask(__name__)


@app.route('/api/process')
def process():
    """ End point to handle the request """
    num1 = request.args.get('num1', type=int)
    num2 = request.args.get('num2', type=int)
    if validate_arguments(num1, num2):
        add_op = AddOperation(num1, num2)
        return make_response({"message": add_op.get_output_json()}, 200)
    return make_response({"message": "bad arguments"}, 400)


def validate_arguments(num1, num2):
    """ Function to validate the arguments """
    if(isinstance(num1, int) and isinstance(num2, int)):
        return True
    return False
