import React from "react";

const pizzas = (props) => {
    return  (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"row"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Name</th>
                            <th scope={"col"}>Sales</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.pizzas.map((term) => {
                            return (
                                <tr>
                                    <td>{term.name}</td>
                                    <td>{term.sales}</td>
                                </tr>
                            )
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default pizzas;
