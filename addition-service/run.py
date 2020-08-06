""" Starting file for service """

from project import app

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
