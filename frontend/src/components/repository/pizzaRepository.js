import axios from "../custom-axios/axios";

const PizzaRepository = {
    fetchPizzas: () => {
        return axios.get("/pizza");
    }
}
export default PizzaRepository;