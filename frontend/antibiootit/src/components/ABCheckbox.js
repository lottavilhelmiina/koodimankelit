export default function ABCheckbox(props) {
    const checkboxLabel = props.label;
    console.log({checkboxLabel})
    return (
        <label className="form--checkbox">
            <input 
                type="checkbox"
            /> {checkboxLabel}
        </label>
    )
}