
import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom';
import Pizzas from "../Pizzas/pizzas";
import PizzaRepository from "../repository/pizzaRepository";
class App extends Component{

  constructor(props) {
      super(props);
      this.state = {
          pizzas: []
      }
  }

  render() {
      return (
          <Router>
              <main>
                  <div className="container">
                      <Route path={"/pizza"} exact render={() => <Pizzas pizzas = {this.state.pizzas}/>}/>
                  </div>
              </main>
          </Router>
      );
  }

  loadPizzas = () => {
      PizzaRepository.fetchPizzas()
          .then((data) => {
              this.setState({
                  pizzas : data.data
              })
          })
  }

    componentDidMount() {
      this.loadPizzas();
  }
}

export default App;
