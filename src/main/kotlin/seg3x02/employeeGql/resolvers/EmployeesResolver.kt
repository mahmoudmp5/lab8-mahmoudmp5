package seg3x02.employeeGql.resolvers

import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeesRepository
import seg3x02.employeeGql.resolvers.types.CreateEmployeeInput


@Component
class EmployeesResolver(
    private val employeesRepository: EmployeesRepository
) : GraphQLQueryResolver, GraphQLMutationResolver {

    fun employees(): List<Employee> = employeesRepository.findAll()

    fun employee(id: Long): Employee? = employeesRepository.findById(id.toString()).orElse(null)

    fun addEmployee(input: CreateEmployeeInput): Employee {
        val employee = Employee(
            name = input.name,
            dateOfBirth = input.dateOfBirth,
            gender = input.gender,
            city = input.city,
            salary = input.salary,
            email = input.email
        )
        return employeesRepository.save(employee)
    }
}
